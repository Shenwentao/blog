package cn.xiaqileyu.blog.constant;

/**
 * 过滤器使用常量类
 *
 * @author swt
 * @date 2020/6/24
 */
public class JwtConstants {

    /**
     * 往header中存放的鉴权键值
     */
    public static String HEADER_AUTH_KEY = "Authorization";

    /**
     * 鉴权token标识
     */
    public static String TOKEN_FLAG = "blog";

    /**
     * 签名密钥
     */
    public static String SIGNING_KEY = "JwtSecret";

    /**
     * 往header中存放的content-type键值
     */
    public static String CONTENT_TYPE_KEY = "content-type";

    /**
     * content-type的值
     */
    public static String CONTENT_TYPE_VALUE = "text/html;charset=UTF-8";
}
