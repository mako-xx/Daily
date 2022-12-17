package com.travelthree.daily.constant;

import lombok.Getter;

/**
 * @author 陈宣辰
 * @date 2022-11-23 15:14:04
 * @description
 */
@Getter
public enum ResultCodeEnum {
    PARAM_VALIDATE_FAILED(1001, "参数校验失败"),
    DB_ERROR(2001, "数据库错误"),
    LOGIN_FAILED(1006, "用户名或密码错误"),
    NOT_LOGIN(3002, "用户没有登录"),
    FORBIDDEN_OP(1002, "不允许的操作"),
    FAILED_OP(1003, "操作失败");

    private final int code;
    private final String message;

    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
