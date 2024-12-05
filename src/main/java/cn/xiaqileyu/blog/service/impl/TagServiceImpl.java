package cn.xiaqileyu.blog.service.impl;

import cn.xiaqileyu.blog.constant.CommonConstants;
import cn.xiaqileyu.blog.constant.ResultCode;
import cn.xiaqileyu.blog.domain.dao.TagDO;
import cn.xiaqileyu.blog.domain.request.IdRequest;
import cn.xiaqileyu.blog.domain.request.TagAddRequest;
import cn.xiaqileyu.blog.domain.request.TagModifyRequest;
import cn.xiaqileyu.blog.domain.request.TagQueryRequest;
import cn.xiaqileyu.blog.domain.vo.PageResultVO;
import cn.xiaqileyu.blog.domain.vo.ResultVO;
import cn.xiaqileyu.blog.domain.vo.TagVO;
import cn.xiaqileyu.blog.exception.CustomException;
import cn.xiaqileyu.blog.mapper.TagMapper;
import cn.xiaqileyu.blog.service.ITagService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 标签接口实现类
 *
 * @author swt
 * @date 2020/7/7
 */
@Slf4j
@Service
public class TagServiceImpl implements ITagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public ResultVO<Object> saveTag(TagAddRequest request) {
        //判断标签是否已经存在
        TagDO tagDO = tagMapper.selectTagByName(request.getTagName());
        if (null != tagDO) {
            log.info("标签已经存在，tagName : {}", request.getTagName());
            throw new CustomException(ResultCode.TAG_NAME_EXIST.getCode(), ResultCode.TAG_NAME_EXIST.getMsg());
        }

        TagDO tagAddDO = new TagDO();
        BeanUtils.copyProperties(request, tagAddDO);
        tagMapper.insertTag(tagAddDO);
        return ResultVO.successResult(CommonConstants.EMPTY_DATA);
    }

    @Override
    public ResultVO<Object> modifyTag(TagModifyRequest request) {
        TagDO tagDO = new TagDO();
        BeanUtils.copyProperties(request, tagDO);
        tagMapper.updateTag(tagDO);
        return ResultVO.successResult(CommonConstants.EMPTY_DATA);
    }

    @Override
    public ResultVO<Object> deleteTag(IdRequest request) {
        tagMapper.deleteTag(request.getId());
        return ResultVO.successResult(CommonConstants.EMPTY_DATA);
    }

    @Override
    public ResultVO<PageResultVO> queryTagList(TagQueryRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<TagDO> tagInfoList = tagMapper.selectTagList(request);
        List<TagVO> tagVOList = tagInfoList.stream().map(tagDO -> JSON.parseObject(JSON.toJSONString(tagDO), TagVO.class))
                .collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(tagVOList);
        return ResultVO.successResult(new PageResultVO(pageInfo.getTotal(), tagVOList));
    }

    @Override
    public ResultVO<List<TagVO>> queryTagAll() {
        TagQueryRequest request = new TagQueryRequest();
        List<TagDO> tagInfoList = tagMapper.selectTagList(request);
        List<TagVO> tagVOList = tagInfoList.stream().map(tagDO -> JSON.parseObject(JSON.toJSONString(tagDO), TagVO.class))
                .collect(Collectors.toList());
        return ResultVO.successResult(tagVOList);
    }
}
