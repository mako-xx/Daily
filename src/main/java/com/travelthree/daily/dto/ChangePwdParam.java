package com.travelthree.daily.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePwdParam {
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;
}
