package cn.xiaqileyu.blog.mapper;

import cn.xiaqileyu.blog.domain.dao.CommentDO;
import cn.xiaqileyu.blog.domain.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 评论管理数据库操作类
 *
 * @author swt
 * @date 2020/7/24
 */
@Mapper
public interface CommentMapper {

    /**
     * 新增一条评论
     *
     * @param commentDO 评论信息
     */
    void insertComment(CommentDO commentDO);

    /**
     * 查询博客评论信息
     *
     * @param commentDO 评论信息
     * @return
     */
    List<CommentVO> selectCommentList(CommentDO commentDO);
}
