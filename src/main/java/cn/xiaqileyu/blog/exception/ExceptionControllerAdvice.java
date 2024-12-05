package cn.xiaqileyu.blog.exception;

import cn.xiaqileyu.blog.constant.ResultCode;
import cn.xiaqileyu.blog.domain.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author swt
 * @date 2020/6/24
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 自定义异常处理
     *
     * @return ResultVO
     */
    @ExceptionHandler(CustomException.class)
    public ResultVO customExceptionHandler(CustomException e) {
        return ResultVO.errorResult(e.getCode(), e.getMsg());
    }

    /**
     * 未知异常处理
     *
     * @return ResultVO
     */
    @ExceptionHandler(Exception.class)
    public ResultVO exceptionHandler(Exception e) {
        // 从异常对象中拿到ObjectError对象
        log.error("未知异常，message: {}", e.getMessage());
        return ResultVO.errorResult(ResultCode.ERROR.getCode(), ResultCode.ERROR.getMsg());
    }

    /**
     * 参数异常处理
     *
     * @return ResultVO
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return ResultVO.errorResult(ResultCode.VALIDATE_FAILED.getCode(), objectError.getDefaultMessage());
    }

}
