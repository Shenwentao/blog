package cn.xiaqileyu.blog.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户登录返回对象
 *
 * @author swt
 * @date 2020/6/29
 */
@Data
@ApiModel(description = "用户登录返回对象")
public class UserLoginVO {

    @ApiModelProperty(value = "用户信息")
    private UserVO user;

    @ApiModelProperty(value = "token")
    private String token;
}
