package com.travelthree.daily.service.impl;

import com.travelthree.daily.constant.AttendanceStatus;
import com.travelthree.daily.mapper.AttendanceMapper;
import com.travelthree.daily.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author faust
* @description 针对表【attendance】的数据库操作Service实现
* @createDate 2022-11-23 15:56:31
*/
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public void attend(String uid) {
        // 更新最新的一次考勤状态
        attendanceMapper.updateRecentStatusByTime(uid, AttendanceStatus.WORK.ordinal());
    }
}




