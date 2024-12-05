package cn.xiaqileyu.blog.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户修改请求对象
 *
 * @author swt
 * @date 2020/6/23
 */
@ApiModel(description = "用户修改请求对象")
@Data
public class UserModifyRequest {

    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "主键id", required = true)
    private Long id;

    @ApiModelProperty(value = "用户账号")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户姓名")
    private String nickname;

    @ApiModelProperty(value = "用户电话")
    private String phone;

    @ApiModelProperty(value = "角色名称")
    private String roleName;
}
