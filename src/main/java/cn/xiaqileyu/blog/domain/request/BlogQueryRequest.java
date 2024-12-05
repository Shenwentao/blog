package cn.xiaqileyu.blog.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 博客文章查询请求对象
 *
 * @author swt
 * @date 2020/7/6
 */
@ApiModel(description = "博客文章查询请求对象")
@Data
public class /**/BlogQueryRequest extends PageRequest{

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "类型id")
    private Long typeId;

    @ApiModelProperty(value = "标签id")
    private Long tagId;
}
