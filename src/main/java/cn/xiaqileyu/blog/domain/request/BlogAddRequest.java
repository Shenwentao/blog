package cn.xiaqileyu.blog.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 博客新增请求对象
 *
 * @author swt
 * @date 2020/6/23
 */
@ApiModel(description = "博客新增请求对象")
@Data
public class BlogAddRequest {

    @NotBlank(message = "博客标题不能为空")
    @ApiModelProperty(value = "博客标题", required = true)
    private String title;

    @NotBlank(message = "博客内容不能为空")
    @ApiModelProperty(value = "博客内容", required = true)
    private String content;

    @NotNull(message = "用户Id不能为空")
    @ApiModelProperty(value = "用户Id", required = true)
    private Long userId;

    @ApiModelProperty(value = "展示图片")
    private String firstPicture;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "类型Id")
    private Long typeId;

    @ApiModelProperty(value = "标签Id")
    private Long tagId;
}
