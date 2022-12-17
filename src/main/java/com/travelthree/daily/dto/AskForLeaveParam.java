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

    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private Integer type;
    @NotBlank
    private String reason;
}
