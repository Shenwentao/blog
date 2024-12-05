package cn.xiaqileyu.blog.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息返回对象
 *
 * @author swt
 * @date 2020/6/29
 */
@Data
@ApiModel(description = "用户信息返回对象")
public class UserVO {

    @ApiModelProperty(value = "博客Id")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户角色")
    private String role;

    @ApiModelProperty(value = "用户电话")
    private String phone;

    @ApiModelProperty(value = "用户姓名")
    private String nickName;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
