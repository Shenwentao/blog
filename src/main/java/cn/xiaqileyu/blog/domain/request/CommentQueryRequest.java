package cn.xiaqileyu.blog.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 评论查询请求类
 *
 * @author swt
 * @date 2020/7/24
 */
@ApiModel(description = "评论查询请求类")
@Data
public class CommentQueryRequest extends PageRequest {

    @NotNull(message = "博客id不能为空")
    @ApiModelProperty(value = "博客id")
    private Long blogId;
}
