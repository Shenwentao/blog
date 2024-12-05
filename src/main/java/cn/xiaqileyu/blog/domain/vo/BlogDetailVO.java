package cn.xiaqileyu.blog.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 博客详细信息返回对象
 *
 * @author swt
 * @date 2020/7/6
 */
@Data
@ApiModel(description = "博客详细信息返回对象")
public class BlogDetailVO {

    @ApiModelProperty(value = "博客Id")
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "访问量")
    private Integer visitNumber;

    @ApiModelProperty(value = "是否发布(0:未发布,1:发布)")
    private Integer isPublished;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "类型id")
    private Long typeId;

    @ApiModelProperty(value = "标签id")
    private Long tagId;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "展示图片")
    private String firstPicture;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
