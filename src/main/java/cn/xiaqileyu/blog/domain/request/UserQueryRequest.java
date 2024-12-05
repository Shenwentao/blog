package cn.xiaqileyu.blog.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户查询请求对象
 *
 * @author swt
 * @date 2020/7/6
 */
@ApiModel(description = "用户查询请求对象")
@Data
public class UserQueryRequest extends PageRequest{

    @ApiModelProperty(value = "用户账号")
    private String userName;
}
