package com.travelthree.daily.service;

import com.github.pagehelper.PageInfo;
import com.travelthree.daily.domain.Leave;
import com.travelthree.daily.dto.AskForLeaveParam;
import com.travelthree.daily.dto.PageParam;

import java.util.List;

/**
* @author faust
* @description 针对表【leave】的数据库操作Service
* @createDate 2022-11-27 16:45:35
*/
public interface LeaveService {

    /**
     * get the employee's all leaves
     *
     * @param employeeId the id of the employee
     * @return leave list
     */
    List<Leave> getAllLeavesByEmployeeId(String employeeId);

//    /**
//     * get the employee's all leaves
//     *
//     * @param employeeId the id of the employee
//     * @param pageParam page related info
//     * @return pageInfo of leaves
//     */
//    PageInfo<Leave> getLeaveHistory(String employeeId, PageParam pageParam);

    /**
     * add a employee's leave
     *
     * @param param leave slip's essential info
     * @param employeeId the id of the employee
     */
    void addLeave(AskForLeaveParam param, String employeeId);


}
