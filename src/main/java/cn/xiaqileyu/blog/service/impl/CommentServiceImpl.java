package cn.xiaqileyu.blog.service.impl;

import cn.xiaqileyu.blog.constant.CommonConstants;
import cn.xiaqileyu.blog.domain.dao.CommentDO;
import cn.xiaqileyu.blog.domain.request.CommentAddRequest;
import cn.xiaqileyu.blog.domain.request.CommentQueryRequest;
import cn.xiaqileyu.blog.domain.vo.CommentVO;
import cn.xiaqileyu.blog.domain.vo.PageResultVO;
import cn.xiaqileyu.blog.domain.vo.ResultVO;
import cn.xiaqileyu.blog.mapper.CommentMapper;
import cn.xiaqileyu.blog.service.ICommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论信息接口实现类
 *
 * @author swt
 * @date 2020/7/27
 */
@Service
public class CommentServiceImpl implements ICommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public ResultVO<Object> saveComment(CommentAddRequest request) {
        CommentDO commentDO = new CommentDO();
        BeanUtils.copyProperties(request, commentDO);
        commentMapper.insertComment(commentDO);
        return ResultVO.successResult(CommonConstants.EMPTY_DATA);
    }

    @Override
    public ResultVO<PageResultVO> queryCommentList(CommentQueryRequest request) {
        CommentDO commentDO = new CommentDO();
        commentDO.setBlogId(request.getBlogId());
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<CommentVO> commentVOList = commentMapper.selectCommentList(commentDO);
        PageInfo pageInfo = new PageInfo(commentVOList);
        return ResultVO.successResult(new PageResultVO(pageInfo.getTotal(), commentVOList));
    }
}
