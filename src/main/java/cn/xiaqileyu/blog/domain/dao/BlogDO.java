package cn.xiaqileyu.blog.domain.dao;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * 博客信息实体类
 *
 * @author swt
 * @date 2020/7/6
 */
@Data
public class BlogDO {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 访问量
     */
    private Integer visitNumber;

    /**
     * 是否发布(0:未发布,1:发布)
     */
    private Integer isPublished;

    /**
     * 是否删除(0:未删除,1:删除)
     */
    private Integer isDelete;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 类型id
     */
    private Long typeId;

    /**
     * 标签id
     */
    private Long tagId;

    /**
     * 描述
     */
    private String description;

    /**
     * 博客展示图片
     */
    private String firstPicture;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
