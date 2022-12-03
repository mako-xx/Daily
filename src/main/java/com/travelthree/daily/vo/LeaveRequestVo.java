package com.travelthree.daily.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestVo {

    private String id;

    private String startDate;

    private String endDate;

    private String status;

    private String type;

    private String reason;

    private String employeeId;

    private String name;
}
