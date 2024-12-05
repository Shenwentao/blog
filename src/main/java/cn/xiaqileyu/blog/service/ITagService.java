package cn.xiaqileyu.blog.service;

import cn.xiaqileyu.blog.domain.request.IdRequest;
import cn.xiaqileyu.blog.domain.request.TagAddRequest;
import cn.xiaqileyu.blog.domain.request.TagModifyRequest;
import cn.xiaqileyu.blog.domain.request.TagQueryRequest;
import cn.xiaqileyu.blog.domain.vo.PageResultVO;
import cn.xiaqileyu.blog.domain.vo.ResultVO;
import cn.xiaqileyu.blog.domain.vo.TagVO;

import java.util.List;

/**
 * 标签信息接口定义
 *
 * @author swt
 * @date 2020/7/7
 */
public interface ITagService {

    /**
     * 保存标签信息
     *
     * @param request 标签信息请求对象
     * @return 新增结果
     */
    ResultVO<Object> saveTag(TagAddRequest request);

    /**
     * 修改标签信息
     *
     * @param request 标签信息请求对象
     * @return 修改结果
     */
    ResultVO<Object> modifyTag(TagModifyRequest request);

    /**
     * 删除标签信息
     *
     * @param request Id通用请求对象
     * @return 删除结果
     */
    ResultVO<Object> deleteTag(IdRequest request);

    /**
     * 查询标签信息列表
     *
     * @param request 标签信息请求对象
     * @return 标签信息列表
     */
    ResultVO<PageResultVO> queryTagList(TagQueryRequest request);

    /**
     * 查询所有标签
     *
     * @return 标签列表
     */
    ResultVO<List<TagVO>> queryTagAll();
}
