package com.travelthree.daily.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import com.travelthree.daily.constant.ResultCodeEnum;
import com.travelthree.daily.constant.RoleEnum;
import com.travelthree.daily.domain.Department;
import com.travelthree.daily.domain.Employee;
import com.travelthree.daily.domain.LeaveType;
import com.travelthree.daily.exception.BusinessException;
import com.travelthree.daily.mapper.AttendanceMapper;
import com.travelthree.daily.mapper.DepartmentMapper;
import com.travelthree.daily.mapper.EmployeeMapper;
import com.travelthree.daily.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author faust
* @description 针对表【department】的数据库操作Service实现
* @createDate 2022-11-23 15:56:31
*/
@Service
public class DepartmentServiceImpl implements DepartmentService {

    public static final String NO_SUPERIOR = "";

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

    @Override
    public void addDepartment(Department department) {
        Department dt = departmentMapper.selectByName(department.getName());
        if (ObjectUtil.isNotNull(dt)) {
            throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "当前部门名称已存在");
        }
        Department department1 = new Department();
        BeanUtils.copyProperties(department, department1);
        String id = UUID.fastUUID().toString();
        department1.setId(id);
        departmentMapper.insert(department1);
    }

    @Override
    public void updateDepartment(Department department, String id) {
        Department dt = departmentMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(dt)) {
            throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "当前部门id不存在");
        }
        Department dt1 = departmentMapper.selectByName(department.getName());
        if (ObjectUtil.isNotNull(dt1)) {
            throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "当前部门名称已存在");
        }
        dt.setName(department.getName());
        dt.setSuperiorId(department.getSuperiorId());
        departmentMapper.updateByPrimaryKeySelective(dt);
    }

    @Override
    public void deleteInfo(String id) {
        Department dt = departmentMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(dt)) {
            throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "当前部门id不存在");
        }
        //删除部门前先检查该部门是否有员工，有的话就不能删除
        List<Employee> employeeList = employeeMapper.selectAllByDepartmentId(id);
        if (employeeList.size() != 0) {
            throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "当前部门还有员工");
        }
        departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Department> selectAllDepartments() {

        return departmentMapper.selectAll();
    }

    @Override
    public String getSuperiorName(Department department) {

        Department superior = departmentMapper.selectByPrimaryKey(department.getSuperiorId());
        if (superior == null) {
            return NO_SUPERIOR;
        }
        return superior.getName();
    }

}




