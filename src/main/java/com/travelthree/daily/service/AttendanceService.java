package com.travelthree.daily.service;

import com.github.pagehelper.PageInfo;
import com.travelthree.daily.domain.Attendance;
import com.travelthree.daily.dto.AttendanceParam;
import com.travelthree.daily.dto.PageParam;

import java.util.List;

/**
* @author faust
* @description 针对表【attendance】的数据库操作Service
* @createDate 2022-11-23 15:56:31
*/
public interface AttendanceService {

    /**
     * 员工参与每日考勤
     * */
    void attend(String uid);

    /**
     * 开始每日考勤
     * */
    void addAttend();

    /**
     * 返回所有的请假信息
     * @param attendanceParam
     * @return
     */
    PageInfo queryAttendanceByDate(AttendanceParam attendanceParam, PageParam pageParam);

    List<Attendance> getAttendancesByEmployeeId(String id);

}
