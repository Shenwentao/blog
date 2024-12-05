package cn.xiaqileyu.blog.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 评论新增请求类
 *
 * @author swt
 * @date 2020/7/24
 */
@ApiModel(description = "评论新增请求类")
@Data
public class CommentAddRequest {

    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id")
    private Long userId;

    @NotNull(message = "博客id不能为空")
    @ApiModelProperty(value = "博客id")
    private Long blogId;

    @NotBlank(message = "评论内容不能为空")
    @ApiModelProperty(value = "评论内容")
    private String content;
}
