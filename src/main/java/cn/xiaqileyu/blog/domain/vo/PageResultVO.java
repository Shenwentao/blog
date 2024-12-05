package cn.xiaqileyu.blog.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 分页返回结果
 *
 * @author swt
 * @date 2020/6/29
 */
@Data
@ApiModel(description = "分页返回结果")
public class PageResultVO {

    @ApiModelProperty(value = "总条数")
    private Long totalSize;

    @ApiModelProperty(value = "数据结果")
    private List<?> data;

    public PageResultVO(Long totalSize, List<?> data) {
        this.totalSize = totalSize;
        this.data = data;
    }
}
