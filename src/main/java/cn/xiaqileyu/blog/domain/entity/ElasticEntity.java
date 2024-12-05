package cn.xiaqileyu.blog.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * es操作类
 *
 * @author swt
 * @date 2020/7/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticEntity<T> {

    /**
     * id
     */
    private String id;

    /**
     * 数据
     */
    private T data;
}