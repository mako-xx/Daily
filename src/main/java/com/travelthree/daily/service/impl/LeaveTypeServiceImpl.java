package com.travelthree.daily.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import com.travelthree.daily.constant.AttendanceStatus;
import com.travelthree.daily.constant.ResultCodeEnum;
import com.travelthree.daily.domain.Attendance;
import com.travelthree.daily.domain.Department;
import com.travelthree.daily.domain.Employee;
import com.travelthree.daily.domain.LeaveType;
import com.travelthree.daily.exception.BusinessException;
import com.travelthree.daily.mapper.AttendanceMapper;
import com.travelthree.daily.mapper.DepartmentMapper;
import com.travelthree.daily.mapper.EmployeeMapper;
import com.travelthree.daily.mapper.LeaveTypeMapper;
import com.travelthree.daily.service.LeaveTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
* @author faust
* @description 针对表【leave_type】的数据库操作Service实现
* @createDate 2022-11-27 16:45:35
*/
@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private LeaveTypeMapper leaveTypeMapper;

    @Override
    public void addLeaveType(String name) {
        LeaveType lt = leaveTypeMapper.selectByName(name);
        if (ObjectUtil.isNotNull(lt)) {
            throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "当前请假类型已存在");
        }
        LeaveType leaveType = new LeaveType();
        leaveType.setName(name);
        leaveTypeMapper.insertSelective(leaveType);
    }

    @Override
    public void updateLeaveType(LeaveType leaveType, Integer id) {
        LeaveType lt = leaveTypeMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(lt)) {
            throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "当前请假类型id不存在");
        }
        lt.setName(leaveType.getName());
        leaveTypeMapper.updateByPrimaryKey(lt);
    }

    @Override
    public void deleteInfo(Integer id) {
        LeaveType lt = leaveTypeMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(lt)) {
            throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "当前请假类型id不存在");
        }
        leaveTypeMapper.deleteByPrimaryKey(id);
    }
}




