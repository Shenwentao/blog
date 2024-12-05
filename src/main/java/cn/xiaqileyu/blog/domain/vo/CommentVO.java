package cn.xiaqileyu.blog.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 评论信息返回对象
 *
 * @author swt
 * @date 2020/7/24
 */
@Data
@ApiModel(description = "评论信息返回对象")
public class CommentVO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "博客id")
    private Long blogId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
