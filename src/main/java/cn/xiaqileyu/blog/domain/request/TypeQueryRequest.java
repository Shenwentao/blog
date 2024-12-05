package cn.xiaqileyu.blog.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分类信息查询请求对象
 *
 * @author swt
 * @date 2020/7/6
 */
@ApiModel(description = "分类信息查询请求对象")
@Data
public class TypeQueryRequest extends PageRequest{

    @ApiModelProperty(value = "分类名称")
    private String typeName;
}
