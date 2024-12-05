package cn.xiaqileyu.blog.domain.dao;

import lombok.Data;

/**
 * 博客标签实体类
 *
 * @author swt
 * @date 2020/7/6
 */
@Data
public class TagDO {

    /**
     * id
     */
    private Long id;

    /**
     * 标签名称
     */
    private String tagName;
}
