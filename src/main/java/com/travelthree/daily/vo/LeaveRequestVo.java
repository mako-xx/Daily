package com.travelthree.daily.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestVo {

    private String id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;

    private String type;

    private String reason;

    private String employeeId;

    private String name;
}
