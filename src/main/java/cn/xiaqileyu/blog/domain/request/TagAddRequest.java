package cn.xiaqileyu.blog.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 标签信息新增请求对象
 *
 * @author swt
 * @date 2020/7/7
 */
@ApiModel(description = "标签信息新增请求对象")
@Data
public class TagAddRequest {

    @NotBlank(message = "标签名称不能为空")
    @ApiModelProperty(value = "标签名称", required = true)
    private String tagName;
}
