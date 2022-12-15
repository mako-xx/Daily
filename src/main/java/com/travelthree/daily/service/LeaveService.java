package com.travelthree.daily.service;

import com.github.pagehelper.PageInfo;
import com.travelthree.daily.constant.LeaveCheckStatus;
import com.travelthree.daily.domain.Leave;
import com.travelthree.daily.dto.AskForLeaveParam;
import com.travelthree.daily.dto.PageParam;
import com.travelthree.daily.vo.LeaveVo;

import java.util.List;


/**
* @author faust
* @description 针对表【leave】的数据库操作Service
* @createDate 2022-11-27 16:45:35
*/
public interface LeaveService {

    Leave getById(String id);

    void updateLeaveStatus(Leave leave);

    PageInfo<LeaveVo> queryLeave(PageParam pageParam, LeaveCheckStatus status);

    List<Leave> getLeavesByEmployeeId(String employeeId);

    /**
     * get the employee's all leaves
     *
     * @param employeeId the id of the employee
     * @return pageInfo of leaves
     */
    PageInfo<Leave> getPageLeaveHistory(String employeeId);

    /**
     * add a employee's leave
     *
     * @param param leave slip's essential info
     * @param employeeId the id of the employee
     */
    void addLeave(AskForLeaveParam param, String employeeId);


}
