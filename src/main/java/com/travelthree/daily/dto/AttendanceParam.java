package com.travelthree.daily.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class AttendanceParam {
    @NotNull
    private LocalDate startDate = LocalDate.now();
    @NotNull
    private LocalDate endDate = LocalDate.now();
}
