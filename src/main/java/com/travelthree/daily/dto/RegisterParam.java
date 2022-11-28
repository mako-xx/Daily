package com.travelthree.daily.dto;

import com.travelthree.daily.constant.RoleEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/** 注册参数 */
@Data
public class RegisterParam {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String telephone;
    @NotBlank
    private String departmentId;
    @NotNull
    private RoleEnum role;
}
