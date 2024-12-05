package cn.xiaqileyu.blog.service.impl;

import cn.xiaqileyu.blog.constant.CacheKeyConstants;
import cn.xiaqileyu.blog.constant.CommonConstants;
import cn.xiaqileyu.blog.constant.ResultCode;
import cn.xiaqileyu.blog.domain.dao.BlogDO;
import cn.xiaqileyu.blog.domain.entity.KafkaEntity;
import cn.xiaqileyu.blog.domain.request.BlogAddRequest;
import cn.xiaqileyu.blog.domain.request.BlogModifyRequest;
import cn.xiaqileyu.blog.domain.request.BlogQueryRequest;
import cn.xiaqileyu.blog.domain.request.IdRequest;
import cn.xiaqileyu.blog.domain.vo.BlogDetailVO;
import cn.xiaqileyu.blog.domain.vo.BlogVO;
import cn.xiaqileyu.blog.domain.vo.ResultVO;
import cn.xiaqileyu.blog.exception.CustomException;
import cn.xiaqileyu.blog.mapper.BlogMapper;
import cn.xiaqileyu.blog.service.IBlogService;
import cn.xiaqileyu.blog.util.ElasticSearchUtil;
import cn.xiaqileyu.blog.util.KafkaProducerUtil;
import cn.xiaqileyu.blog.util.RedisUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 博客信息接口实现类
 *
 * @author swt
 * @date 2020/7/10
 */
@Slf4j
@Service
public class BlogServiceImpl implements IBlogService {

    @Resource
    private BlogMapper blogMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private KafkaProducerUtil kafkaProducerUtil;

    @Autowired
    private ElasticSearchUtil elasticSearchUtil;

    @Override
    public ResultVO<Object> saveBlog(BlogAddRequest request) {
        //验证博客标题是否存在
        BlogDO blogValid = new BlogDO();
        blogValid.setTitle(request.getTitle());
        BlogDO blogDO = blogMapper.selectOneBlog(blogValid);
        if (blogDO != null) {
            log.info("标题已经存在，title : {}", request.getTitle());
            throw new CustomException(ResultCode.BLOG_TITLE_EXIST.getCode(), ResultCode.BLOG_TITLE_EXIST.getMsg());
        }
        BlogDO blogAddDO = new BlogDO();
        blogAddDO.setIsDelete(CommonConstants.IS_DELETE_FALSE);
        blogAddDO.setIsPublished(CommonConstants.IS_PUBLISH_FALSE);
        blogAddDO.setVisitNumber(CommonConstants.INIT_VISIT_NUMBER);
        BeanUtils.copyProperties(request, blogAddDO);
        blogMapper.insertBlog(blogAddDO);
        //通过kafka同步数据至es
        sendKafkaMessage(blogAddDO);
        return ResultVO.successResult(CommonConstants.EMPTY_DATA);
    }

    @Override
    public ResultVO<Object> modifyBlog(BlogModifyRequest request) {
        BlogDO blogDO = new BlogDO();
        BeanUtils.copyProperties(request, blogDO);
        blogMapper.updateBlog(blogDO);
        //删除缓存
        String hKey = String.format(CacheKeyConstants.REDIS_BLOG_INFO, request.getId());
        redisUtil.hdel(CacheKeyConstants.REDIS_BLOG_ARTICLE, hKey);
        //通过kafka同步数据至es
        sendKafkaMessage(blogDO);
        return ResultVO.successResult(CommonConstants.EMPTY_DATA);
    }

    @Override
    public ResultVO<Object> deleteBlog(IdRequest request) {
        BlogDO blogDO = new BlogDO();
        blogDO.setId(request.getId());
        blogDO.setIsDelete(CommonConstants.IS_DELETE_TRUE);
        blogMapper.updateBlog(blogDO);
        //删除缓存
        String hKey = String.format(CacheKeyConstants.REDIS_BLOG_INFO, request.getId());
        redisUtil.hdel(CacheKeyConstants.REDIS_BLOG_ARTICLE, hKey);
        //通过kafka同步数据至es
        KafkaEntity kafkaEntity = new KafkaEntity();
        kafkaEntity.setType("delete");
        kafkaEntity.setId(String.valueOf(request.getId()));
        kafkaProducerUtil.sendMessage("blog_es", kafkaEntity);
        return ResultVO.successResult(CommonConstants.EMPTY_DATA);
    }

    @Override
    public ResultVO<BlogVO> queryBlogList(BlogQueryRequest request) {
        //通过es查询
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (StringUtils.isNotBlank(request.getTitle())) {
            boolQueryBuilder.filter(QueryBuilders.queryStringQuery(request.getTitle()).field("title"));
        }
        if (null != request.getTypeId()) {
            boolQueryBuilder.filter(QueryBuilders.matchQuery("typeId", request.getTypeId()));
        }
        if (null != request.getTagId()) {
            boolQueryBuilder.filter(QueryBuilders.matchQuery("tagId", request.getTagId()));
        }
        SearchSourceBuilder builder = new SearchSourceBuilder();
        int pageNum = (request.getPageNum() - 1) * request.getPageSize();
        //from定义查询开始的位置
        builder.size(request.getPageSize()).from(pageNum).query(boolQueryBuilder);
        List<BlogVO> blogVOList = elasticSearchUtil.search(ElasticSearchUtil.INDEX_NAME, builder, BlogVO.class);
        return ResultVO.successResult(blogVOList);
    }

    @Override
    public ResultVO<BlogDetailVO> queryBlogDetail(IdRequest request) {
        BlogDO blogDO = new BlogDO();
        blogDO.setId(request.getId());
        //从缓存中获取博客信息
        String hKey = String.format(CacheKeyConstants.REDIS_BLOG_INFO, request.getId());
        BlogDetailVO blogCacheInfo = JSON.parseObject(redisUtil.hget(CacheKeyConstants.REDIS_BLOG_ARTICLE, hKey), BlogDetailVO.class);
        //缓存不为空，直接返回缓存信息
        if (blogCacheInfo != null) {
            //访问量递增
            blogDO.setVisitNumber(blogCacheInfo.getVisitNumber() + 1);
            blogMapper.updateBlog(blogDO);
            return ResultVO.successResult(blogCacheInfo);
        }
        //缓存为空，从数据库中获取博客信息并存入缓存中
        BlogDO blogInfoDO = blogMapper.selectOneBlog(blogDO);
        BlogDetailVO blogInfoVO = new BlogDetailVO();
        BeanUtils.copyProperties(blogInfoDO, blogInfoVO);
        redisUtil.hset(CacheKeyConstants.REDIS_BLOG_ARTICLE, hKey, JSON.toJSONString(blogInfoDO), CommonConstants.MILLISECONDS_PER_DAY);
        //访问量递增
        blogDO.setVisitNumber(blogInfoDO.getVisitNumber() + 1);
        blogMapper.updateBlog(blogDO);
        return ResultVO.successResult(blogInfoVO);
    }

    @Override
    public ResultVO<Object> publishBlog(IdRequest request) {
        BlogDO blogDO = new BlogDO();
        blogDO.setId(request.getId());
        blogDO.setIsPublished(CommonConstants.IS_PUBLISH_TRUE);
        blogMapper.updateBlog(blogDO);
        return ResultVO.successResult(CommonConstants.EMPTY_DATA);
    }

    private void sendKafkaMessage(BlogDO blogDO) {
        BlogDO blog = new BlogDO();
        blog.setId(blogDO.getId());
        BlogDO blogInfo = blogMapper.selectOneBlog(blog);
        KafkaEntity kafkaEntity = new KafkaEntity();
        kafkaEntity.setType("post");
        kafkaEntity.setData(blogInfo);
        kafkaEntity.setId(String.valueOf(blogInfo.getId()));
        kafkaProducerUtil.sendMessage("blog_es", kafkaEntity);
    }
}
