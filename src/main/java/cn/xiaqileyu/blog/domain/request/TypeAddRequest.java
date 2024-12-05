package cn.xiaqileyu.blog.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 分类信息新增请求对象
 *
 * @author swt
 * @date 2020/7/7
 */
@ApiModel(description = "分类信息新增请求对象")
@Data
public class TypeAddRequest {

    @NotBlank(message = "分类名称不能为空")
    @ApiModelProperty(value = "分类名称", required = true)
    private String typeName;
}
