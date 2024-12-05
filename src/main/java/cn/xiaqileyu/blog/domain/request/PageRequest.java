package cn.xiaqileyu.blog.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 分页请求对象
 *
 * @author swt
 * @date 2020/6/23
 */
@ApiModel(description = "分页请求对象")
@Data
public class PageRequest{

    @ApiModelProperty(value = "当前页码,默认为1")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页数量,默认为10")
    private Integer pageSize = 10;
}
