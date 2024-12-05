package cn.xiaqileyu.blog.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * kafka操作类
 *
 * @author swt
 * @date 2020/8/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaEntity<T> {

    /**
     * 操作类型 post：插入|修改 delete：删除
     */
    private String type;

    /**
     * id
     */
    private String id;

    /**
     * 数据
     */
    private T data;
}
