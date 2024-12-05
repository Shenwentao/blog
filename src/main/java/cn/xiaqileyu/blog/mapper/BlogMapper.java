package cn.xiaqileyu.blog.mapper;

import cn.xiaqileyu.blog.domain.dao.BlogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * 博客管理数据库操作类
 *
 * @author swt
 * @date 2020/6/30
 */
@Mapper
public interface BlogMapper {

    /**
     * 查询一条博客信息
     *
     * @param blogDO 博客信息
     * @return 分类信息
     */
    BlogDO selectOneBlog(BlogDO blogDO);

    /**
     * 新增博客信息
     *
     * @param blogDO 博客信息
     */
    void insertBlog(BlogDO blogDO);

    /**
     * 修改博客信息
     *
     * @param blogDO 博客信息
     */
    void updateBlog(BlogDO blogDO);

    /**
     * 清理被删除时间大于30天的博客文章
     *
     * @param date 30天前的时间
     */
    void deleteRecycleBlog(Date date);
}
