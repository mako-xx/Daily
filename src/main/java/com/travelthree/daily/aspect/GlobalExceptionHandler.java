package com.travelthree.daily.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelthree.daily.constant.ResultCodeEnum;
import com.travelthree.daily.exception.BusinessException;
import com.travelthree.daily.exception.IllegalOperationException;
import com.travelthree.daily.vo.CommonResult;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.util.List;

/**
 * @author 陈宣辰
 * @date 2022-11-23 15:11:49
 * @description
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ObjectMapper objectMapper;


    /**
     * 参数校验失败的异常处理器
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public CommonResult handleBindException(BindException e, HttpServletRequest request) {
        String message = formatBindException(e);
        log.warn(formatException(e, request, message, false));
        return CommonResult.failure(ResultCodeEnum.PARAM_VALIDATE_FAILED.getCode(), message);
    }


    /**
     * 请求方式不支持
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public CommonResult handleMethodNotAllowed(Exception e, HttpServletRequest request) {
        log.warn(formatException(e, request, null, false));
        return CommonResult.failure("请求方式不支持");
    }


    /**
     * 请求格式不对
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ServletRequestBindingException.class, MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    @ResponseBody
    public CommonResult handleBadRequest(Exception e, HttpServletRequest request) {
        log.warn(formatException(e, request, null, false));
        return CommonResult.failure("请求格式不对");
    }


    /**
     * 请求的操作非法
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(IllegalOperationException.class)
    @ResponseBody
    public CommonResult handleIllegalOperationException(Exception e, HttpServletRequest request) {
        log.warn(formatException(e, request, null, false));
        return CommonResult.forbidden(e.getMessage());
    }

    /**
     * 请求URL有误，无法解析这个URL该对应Controller中哪个方法
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public CommonResult handleNotFoundException(NoHandlerFoundException e, HttpServletRequest request) {
        log.warn(formatException(e, request, null, false));
        return CommonResult.failure("请求URL不存在");
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public CommonResult handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        log.warn(formatException(e, request, null, false));
        return CommonResult.forbidden(403, e.getMessage());
    }

    /**
     * 参数校验失败异常处理
     * */
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public CommonResult handleValidationException(ValidationException e, HttpServletRequest request) {
        log.warn("参数校验出错，错误信息：{}", e.getMessage());
        String msg = e.getMessage();
        msg = msg.substring(msg.indexOf(".") + 1);
        return CommonResult.failure(ResultCodeEnum.PARAM_VALIDATE_FAILED.getCode(), msg);
    }

    /**
     * 业务异常，可细分为多种情况，可见ResultCodeEnum
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public CommonResult handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.warn(formatException(e, request, null, true));
        return CommonResult.failure(e.getCode(), e.getMessage());
    }


    /**
     * Exception兜底
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request) {
        // TODO: 服务器错误处理逻辑完善
        e.printStackTrace();
        return "error";
    }


    /**
     * 格式化异常信息
     */
    @SneakyThrows
    public String formatException(Exception e, HttpServletRequest request, String message, boolean stackRequired) {
        StringBuilder sb = new StringBuilder();
        sb.append("(异常)")
                .append("<类型>").append(e.getClass())
                .append("<信息>").append(message != null ? message : e.getMessage());
        if (stackRequired) {
            sb.append("<堆栈>").append(objectMapper.writeValueAsString(e));
        }
        return sb.toString();
    }


    /**
     * 把异常信息格式化成自己喜欢的格式，这个方法用于格式化BindException
     */
    public String formatBindException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (FieldError error : fieldErrors) {
            //提示：error.getField()得到的是校验失败的字段名字，error.getDefaultMessage()得到的是校验失败的原因
            sb.append(error.getField())
                    .append("=[")
                    .append(error.getDefaultMessage())
                    .append("]  ");
        }
        return sb.toString();

    }
}
