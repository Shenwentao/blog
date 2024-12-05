package cn.xiaqileyu.blog.service;

import cn.xiaqileyu.blog.domain.request.CommentAddRequest;
import cn.xiaqileyu.blog.domain.request.CommentQueryRequest;
import cn.xiaqileyu.blog.domain.vo.PageResultVO;
import cn.xiaqileyu.blog.domain.vo.ResultVO;

/**
 * 评论信息接口定义
 *
 * @author swt
 * @date 2020/7/27
 */
public interface ICommentService {

    /**
     * 新增评论信息
     *
     * @param request 评论信息请求对象
     */
    ResultVO<Object> saveComment(CommentAddRequest request);

    /**
     * 查询评论信息
     *
     * @param request 评论信息请求对象
     * @return 评论信息集合
     */
    ResultVO<PageResultVO> queryCommentList(CommentQueryRequest request);
}
