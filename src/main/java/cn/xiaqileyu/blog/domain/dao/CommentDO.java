package cn.xiaqileyu.blog.domain.dao;

import lombok.Data;

import java.util.Date;

/**
 * 评论信息实体类
 *
 * @author swt
 * @date 2020/7/24
 */
@Data
public class CommentDO {

    /**
     * id
     */
    private Long id;

    /**
     * id
     */
    private Long userId;

    /**
     * id
     */
    private Long blogId;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;
}
