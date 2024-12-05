package cn.xiaqileyu.blog.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 博客修改请求对象
 *
 * @author swt
 * @date 2020/6/23
 */
@ApiModel(description = "博客修改请求对象")
@Data
public class BlogModifyRequest {

    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "主键id", required = true)
    private Long id;

    @NotBlank(message = "博客标题不能为空")
    @ApiModelProperty(value = "博客标题", required = true)
    private String title;

    @ApiModelProperty(value = "展示图片")
    private String firstPicture;

    @ApiModelProperty(value = "类型Id")
    private Long typeId;

    @ApiModelProperty(value = "标签Id")
    private Long tagId;

    @ApiModelProperty(value = "博客内容")
    private String content;

    @ApiModelProperty(value = "描述")
    private String description;
}
