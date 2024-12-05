package cn.xiaqileyu.blog.constant;

/**
 * 缓存Key常量定义
 *
 * @author swt
 * @date 2020/6/24
 */
public class CacheKeyConstants {

    /**
     * 博客文章缓存Redis key
     */
    public static final String REDIS_BLOG_ARTICLE = "blogArticle";

    /**
     * 博客文章缓存Hash key 代表所有博客文章
     */
    public static final String REDIS_BLOG_ALL = "blogALL";

    /**
     * 博客文章缓存Hash key 代表博客文章信息
     */
    public static final String REDIS_BLOG_INFO = "%s.blogInfo";
}
