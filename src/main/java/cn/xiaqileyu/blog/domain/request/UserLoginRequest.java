package cn.xiaqileyu.blog.domain.request;

import lombok.Data;

/**
 * 用户登录请求对象
 *
 * @author swt
 * @date 2020/6/23
 */
@Data
public class UserLoginRequest {

    /**
     * 登陆名称
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;
}
