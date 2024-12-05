package cn.xiaqileyu.blog.domain.vo;

import cn.xiaqileyu.blog.constant.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 返回结果通用类
 *
 * @author swt
 * @date 2020/6/24
 */
@Data
@ApiModel("统一响应体")
public class ResultVO<T> {

    @ApiModelProperty(value = "状态码", notes = "默认1000是成功")
    private Integer code;

    @ApiModelProperty(value = "响应信息", notes = "来说明响应情况")
    private String msg;

    @ApiModelProperty(value = "响应的具体数据")
    private T data;

    /**
     * 成功
     *
     * @param data 业务对象
     * @return 返回结果
     */
    public static <T> ResultVO successResult (T data) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(ResultCode.SUCCESS.getCode());
        resultVO.setMsg(ResultCode.SUCCESS.getMsg());
        resultVO.setData(data);
        return resultVO;
    }

    /**
     * 失败
     *
     * @param code 业务码
     * @param message 返回消息
     * @return 返回结果
     */
    public static ResultVO errorResult (int code, String message) {
        ResultVO resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMsg(message);
        return resultVO;
    }
}
