package com.travelthree.daily.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 陈宣辰
 * @date 2022-11-23 16:58:00
 * @description 登录参数
 */
@Data
public class LoginParam {

    @NotBlank(message = "用户名不可为空")
    private String username;
    @NotBlank(message = "密码不可为空")
    private String password;
    private Boolean remember;
}
