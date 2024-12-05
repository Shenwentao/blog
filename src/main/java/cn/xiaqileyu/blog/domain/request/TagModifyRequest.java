package cn.xiaqileyu.blog.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 标签信息修改请求对象
 *
 * @author swt
 * @date 2020/6/23
 */
@ApiModel(description = "标签信息修改请求对象")
@Data
public class TagModifyRequest {

    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "主键id", required = true)
    private Long id;

    @ApiModelProperty(value = "标签名称")
    private String tagName;
}
