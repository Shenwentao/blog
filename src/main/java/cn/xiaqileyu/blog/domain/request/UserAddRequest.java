package cn.xiaqileyu.blog.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户新增请求对象
 *
 * @author swt
 * @date 2020/6/23
 */
@ApiModel(description = "用户新增请求对象")
@Data
public class UserAddRequest {

    @NotBlank(message = "用户账号不能为空")
    @ApiModelProperty(value = "用户账号", required = true)
    private String userName;

    @NotBlank(message = "用户密码不能为空")
    @ApiModelProperty(value = "用户密码", required = true)
    private String password;

    @NotBlank(message = "用户姓名不能为空")
    @ApiModelProperty(value = "用户姓名", required = true)
    private String nickname;

    @ApiModelProperty(value = "用户电话")
    private String phone;

    @ApiModelProperty(value = "角色名称")
    private String roleName;
}
