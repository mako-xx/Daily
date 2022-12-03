package com.travelthree.daily.service;

import com.travelthree.daily.domain.Leave;

/**
* @author faust
* @description 针对表【leave】的数据库操作Service
* @createDate 2022-11-27 16:45:35
*/
public interface LeaveService {

    Leave getById(String id);

    void updateLeaveStatus(Leave leave);
}
