package com.travelthree.daily.service.impl;

import com.travelthree.daily.domain.Department;
import com.travelthree.daily.mapper.AttendanceMapper;
import com.travelthree.daily.mapper.DepartmentMapper;
import com.travelthree.daily.mapper.EmployeeMapper;
import com.travelthree.daily.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
* @author faust
* @description 针对表【department】的数据库操作Service实现
* @createDate 2022-11-23 15:56:31
*/
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public Department selectByPrimaryKey(String id) {
        Department department = departmentMapper.selectByPrimaryKey(id);
        return department;
    }
}




