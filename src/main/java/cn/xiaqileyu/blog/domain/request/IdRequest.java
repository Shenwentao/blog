package cn.xiaqileyu.blog.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Id请求对象
 *
 * @author swt
 * @date 2020/6/23
 */
@ApiModel(description = "Id请求对象")
@Data
public class IdRequest {

    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "主键id", required = true)
    private Long id;
}
