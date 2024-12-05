package cn.xiaqileyu.blog.domain.dao;

import lombok.Data;

/**
 * 博客分类实体类
 *
 * @author swt
 * @date 2020/7/6
 */
@Data
public class TypeDO {

    /**
     * id
     */
    private Long id;

    /**
     * 分类名称
     */
    private String typeName;
}
