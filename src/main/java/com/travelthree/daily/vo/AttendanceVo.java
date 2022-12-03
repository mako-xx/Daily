package com.travelthree.daily.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceVo {

    private String id;

    private String date;

    private String employeeName;

    private String status;
}
