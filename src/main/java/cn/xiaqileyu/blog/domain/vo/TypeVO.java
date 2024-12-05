package cn.xiaqileyu.blog.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分类信息返回对象
 *
 * @author swt
 * @date 2020/7/6
 */
@Data
@ApiModel(description = "分类信息返回对象")
public class TypeVO {

    @ApiModelProperty(value = "分类Id")
    private Long id;

    @ApiModelProperty(value = "分类名称")
    private String typeName;
}
