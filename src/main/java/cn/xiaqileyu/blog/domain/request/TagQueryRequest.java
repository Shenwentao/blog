package cn.xiaqileyu.blog.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 标签信息查询请求对象
 *
 * @author swt
 * @date 2020/7/6
 */
@ApiModel(description = "标签信息查询请求对象")
@Data
public class TagQueryRequest extends PageRequest{

    @ApiModelProperty(value = "标签名称")
    private String tagName;
}
