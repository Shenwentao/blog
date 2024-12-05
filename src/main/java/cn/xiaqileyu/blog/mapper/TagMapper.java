package cn.xiaqileyu.blog.mapper;

import cn.xiaqileyu.blog.domain.dao.TagDO;
import cn.xiaqileyu.blog.domain.request.TagQueryRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 标签信息数据库操作类
 *
 * @author swt
 * @date 2020/7/7
 */
@Mapper
public interface TagMapper {

    /**
     * 通过标签名查询标签信息
     *
     * @param tagName 标签名称
     * @return 标签信息
     */
    TagDO selectTagByName(@Param(value = "tagName") String tagName);

    /**
     * 新增标签
     *
     * @param tagDO 标签信息
     */
    void insertTag(TagDO tagDO);

    /**
     * 修改标签
     *
     * @param tagDO 标签信息
     */
    void updateTag(TagDO tagDO);

    /**
     * 删除标签
     *
     * @param id 标签ID
     */
    void deleteTag(@Param(value = "id") Long id);

    /**
     * 查询标签信息列表
     *
     * @param request 标签信息查询对象
     * @return 标签信息列表
     */
    List<TagDO> selectTagList(TagQueryRequest request);
}
