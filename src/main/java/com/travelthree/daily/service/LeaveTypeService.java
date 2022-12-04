package com.travelthree.daily.service;

import com.travelthree.daily.domain.LeaveType;

/**
* @author faust
* @description 针对表【leave_type】的数据库操作Service
* @createDate 2022-11-27 16:45:35
*/
public interface LeaveTypeService {
    void addLeaveType(String name);

    void updateLeaveType(LeaveType leaveType, Integer id);

    void deleteInfo(Integer id);

    LeaveType selectById(Integer id);

}
