package com.travelthree.daily.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jiayi Zhu
 * @description
 * @createDate 2022/12/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveVo {

    private String id;

    private String startDate;

    private String endDate;

    private String status;

    private String type;

    private String reason;

    private String employeeId;

    private String name;
}
