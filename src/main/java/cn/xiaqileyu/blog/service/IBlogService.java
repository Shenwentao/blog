package cn.xiaqileyu.blog.service;

import cn.xiaqileyu.blog.domain.request.BlogAddRequest;
import cn.xiaqileyu.blog.domain.request.BlogModifyRequest;
import cn.xiaqileyu.blog.domain.request.BlogQueryRequest;
import cn.xiaqileyu.blog.domain.request.IdRequest;
import cn.xiaqileyu.blog.domain.vo.BlogDetailVO;
import cn.xiaqileyu.blog.domain.vo.BlogVO;
import cn.xiaqileyu.blog.domain.vo.ResultVO;

/**
 * 博客信息接口定义
 *
 * @author swt
 * @date 2020/7/10
 */
public interface IBlogService {

    /**
     * 保存博客信息
     *
     * @param request 博客信息请求对象
     * @return 新增结果
     */
    ResultVO<Object> saveBlog(BlogAddRequest request);

    /**
     * 修改博客信息
     *
     * @param request 博客信息请求对象
     * @return 修改结果
     */
    ResultVO<Object> modifyBlog(BlogModifyRequest request);

    /**
     * 删除博客信息
     *
     * @param request Id通用请求对象
     * @return 删除结果
     */
    ResultVO<Object> deleteBlog(IdRequest request);

    /**
     * 查询博客信息列表
     *
     * @param request 博客信息请求对象
     * @return 博客信息列表
     */
    ResultVO<BlogVO> queryBlogList(BlogQueryRequest request);

    /**
     * 查询博客详情
     *
     * @param request Id通用请求对象
     * @return 博客信息
     */
    ResultVO<BlogDetailVO> queryBlogDetail(IdRequest request);

    /**
     * 发布博客
     *
     * @param request Id通用请求对象
     * @return 发布结果
     */
    ResultVO<Object> publishBlog(IdRequest request);
}
