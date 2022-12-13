package com.travelthree.daily.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Jiayi Zhu
 * @description
 * @createDate 2022/12/4
 */
@Data
public class AskForLeaveParam {

    @NotBlank
    private String startDate;
    @NotBlank
    private String endDate;
    @NotNull
    private Integer type;
    @NotBlank
    private String reason;
}