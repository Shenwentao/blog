package cn.xiaqileyu.blog.controller;

import cn.xiaqileyu.blog.domain.request.CommentAddRequest;
import cn.xiaqileyu.blog.domain.request.CommentQueryRequest;
import cn.xiaqileyu.blog.domain.vo.PageResultVO;
import cn.xiaqileyu.blog.domain.vo.ResultVO;
import cn.xiaqileyu.blog.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 评论信息服务接口
 *
 * @author swt
 * @date 2020/7/24
 */
@RequestMapping("comment")
@RestController
@Api(tags = "评论信息服务接口")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @ApiOperation(value = "新增评论")
    @PostMapping("/saveComment")
    public ResultVO<Object> saveComment(@RequestBody @Valid CommentAddRequest request) {
        return commentService.saveComment(request);
    }

    @ApiOperation(value = "查询评论")
    @PostMapping("/queryCommentList")
    public ResultVO<PageResultVO> queryCommentList(@RequestBody @Valid CommentQueryRequest request) {
        return commentService.queryCommentList(request);
    }
}
