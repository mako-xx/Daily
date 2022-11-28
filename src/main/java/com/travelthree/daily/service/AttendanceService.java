package com.travelthree.daily.service;

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
}
