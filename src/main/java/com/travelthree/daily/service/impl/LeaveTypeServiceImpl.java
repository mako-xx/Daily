package com.travelthree.daily.service.impl;

import com.travelthree.daily.domain.LeaveType;
import com.travelthree.daily.mapper.LeaveTypeMapper;
import com.travelthree.daily.service.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author faust
* @description 针对表【leave_type】的数据库操作Service实现
* @createDate 2022-11-27 16:45:35
*/
@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

    @Autowired
    LeaveTypeMapper leaveTypeMapper;

    /**
     * 获取所有请假类型
     *
     * @return 请假类型列表
     */
    @Override
    public List<LeaveType> getAllLeaveTypes() {

        return leaveTypeMapper.selectAll();
    }
}




