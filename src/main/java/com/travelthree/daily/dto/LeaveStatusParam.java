package com.travelthree.daily.dto;

import com.travelthree.daily.constant.LeaveCheckStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LeaveStatusParam {
    @NotNull
    private LeaveCheckStatus status;
}
