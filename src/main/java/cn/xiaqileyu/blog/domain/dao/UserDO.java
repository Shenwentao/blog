package cn.xiaqileyu.blog.domain.dao;

import lombok.Data;

import java.util.Date;

/**
 * 用户信息实体类
 *
 * @author swt
 * @date 2020/6/29
 */
@Data
public class UserDO {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户角色
     */
    private String roleName;

    /**
     * 用户电话
     */
    private String phone;

    /**
     * 用户姓名
     */
    private String nickname;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
