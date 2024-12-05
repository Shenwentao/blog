package cn.xiaqileyu.blog.constant;

import lombok.Getter;

/**
 * 响应码枚举
 *
 * @author swt
 * @date 2020/6/24
 */
@Getter
public enum ResultCode {

    SUCCESS(200, "操作成功"),

    ERROR(500, "未知错误"),

    TOKEN_FAILURE(401,"token失效"),

    FAILED(1001, "响应失败"),

    VALIDATE_FAILED(1002, "参数校验失败"),

    PASSWORD_IS_INCORRECT(1003,"密码错误"),

    USER_NOT_EXIST(1004,"用户不存在"),

    LOGIN_INFO_IS_EMPTY(1005,"登陆信息为空"),

    LOGIN_NAME_IS_EMPTY(1006,"登陆名称为空"),

    LOGIN_PASSWORD_IS_EMPTY(1007,"登陆密码为空"),

    USER_NAME_EXIST(1008,"用户名已经存在"),

    TAG_NAME_EXIST(1009,"标签已经存在"),

    TYPE_NAME_EXIST(1010,"分类已经存在"),

    BLOG_TITLE_EXIST(1011,"标题已经存在");


    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
