package cn.xiaqileyu.blog.exception;

import lombok.Getter;

/**
 * 自定义异常
 *
 * @author swt
 * @date 2020/6/24
 */
@Getter
public class CustomException extends RuntimeException {

    private int code;

    private String msg;

    public CustomException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
