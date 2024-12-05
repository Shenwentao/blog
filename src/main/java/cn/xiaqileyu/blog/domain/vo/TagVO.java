package cn.xiaqileyu.blog.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 标签信息返回对象
 *
 * @author swt
 * @date 2020/7/6
 */
@Data
@ApiModel(description = "标签信息返回对象")
public class TagVO {

    @ApiModelProperty(value = "标签Id")
    private Long id;

    @ApiModelProperty(value = "标签名称")
    private String tagName;
}
