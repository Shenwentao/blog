package cn.xiaqileyu.blog.controller;

import cn.xiaqileyu.blog.domain.request.BlogAddRequest;
import cn.xiaqileyu.blog.domain.request.BlogModifyRequest;
import cn.xiaqileyu.blog.domain.request.BlogQueryRequest;
import cn.xiaqileyu.blog.domain.request.IdRequest;
import cn.xiaqileyu.blog.domain.vo.BlogDetailVO;
import cn.xiaqileyu.blog.domain.vo.BlogVO;
import cn.xiaqileyu.blog.domain.vo.ResultVO;
import cn.xiaqileyu.blog.service.IBlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 博客管理服务接口
 *
 * @author swt
 * @date 2020/7/6
 */
@RequestMapping("blog")
@RestController
@Api(tags = "博客管理服务接口")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @ApiOperation(value = "新增博客")
    @PostMapping("/saveBlog")
    public ResultVO<Object> saveBlog(@RequestBody @Valid BlogAddRequest request) {
        return blogService.saveBlog(request);
    }

    @ApiOperation(value = "修改博客")
    @PutMapping("/modify log")
    public ResultVO<Object> modifyBlog(@RequestBody @Valid BlogModifyRequest request) {
        return blogService.modifyBlog(request);
    }

    @ApiOperation(value = "删除博客")
    @DeleteMapping("/deleteBlog")
    public ResultVO<Object> deleteBlog(@RequestBody @Valid IdRequest request) {
        return blogService.deleteBlog(request);
    }

    @ApiOperation(value = "查询博客列表")
    @PostMapping("/queryBlogList")
    public ResultVO<BlogVO> queryBlogList(@RequestBody @Valid BlogQueryRequest request) {
        return blogService.queryBlogList(request);
    }

    @ApiOperation(value = "根据id查询博客详情信息")
    @PostMapping("/queryBlogDetail")
    public ResultVO<BlogDetailVO> queryBlogDetail(@RequestBody @Valid IdRequest request) {
        return blogService.queryBlogDetail(request);
    }

    @ApiOperation(value = "发布博客")
    @PostMapping("/publishBlog")
    public ResultVO<Object> publishBlog(@RequestBody @Valid IdRequest request) {
        return blogService.publishBlog(request);
    }
}
