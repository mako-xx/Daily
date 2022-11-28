package com.travelthree.daily.dto;

import com.travelthree.daily.constant.RoleEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 *  更新员工信息参数
 * */
@Data
public class UpdateEmployeeParam {
    @NotBlank
    private String name;
    @NotBlank
    private String telephone;
    @NotBlank
    private String departmentId;

    private RoleEnum role;
}
