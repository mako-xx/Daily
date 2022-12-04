package com.travelthree.daily.dto;

import com.travelthree.daily.constant.LeaveCheckStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LeaveStatusParam {
    @NotBlank
    private LeaveCheckStatus status;
}
