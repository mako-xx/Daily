package com.travelthree.daily.service;

import com.github.pagehelper.PageInfo;
import com.travelthree.daily.domain.Leave;
import com.travelthree.daily.dto.PageParam;

import java.util.List;

import com.travelthree.daily.domain.Leave;
import com.travelthree.daily.dto.AskForLeaveParam;

/**
* @author faust
* @description 针对表【leave】的数据库操作Service
* @createDate 2022-11-27 16:45:35
*/
public interface LeaveService {

    Leave getById(String id);

    void updateLeaveStatus(Leave leave);

    PageInfo queryLeave(PageParam pageParam, Integer status);
    /**
     * add a employee's leave
     *
     * @param param employee's id and leave slip
     */
    void addLeave(AskForLeaveParam param, String employeeId);


}
