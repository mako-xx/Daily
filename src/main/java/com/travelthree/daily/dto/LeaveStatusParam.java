package com.travelthree.daily.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LeaveStatusParam {
    @NotBlank
    private String status;
}
