package com.travelthree.daily.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import com.travelthree.daily.constant.ResultCodeEnum;
import com.travelthree.daily.constant.RoleEnum;
import com.travelthree.daily.domain.Department;
import com.travelthree.daily.domain.Employee;
import com.travelthree.daily.dto.AdminUserDetails;
import com.travelthree.daily.dto.EmployeeDTO;
import com.travelthree.daily.dto.RegisterParam;
import com.travelthree.daily.exception.BusinessException;
import com.travelthree.daily.mapper.DepartmentMapper;
import com.travelthree.daily.mapper.EmployeeMapper;
import com.travelthree.daily.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
* @author faust
* @description 针对表【employee】的数据库操作Service实现
* @createDate 2022-11-23 15:56:31
*/
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public EmployeeDTO getEmployeeByUsername(String username) {
        Employee employee = employeeMapper.selectOneByUsername(username);
        if (employee == null) {
            return null;
        }
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        employeeDTO.setRole(RoleEnum.getRoleFromOrdinal(employee.getRole()));
        return employeeDTO;
    }

    @Override
    public EmployeeDTO login(String username, String password) {
        EmployeeDTO employeeDTO = getEmployeeByUsername(username);
        // 校验密码是否正确
        if (employeeDTO == null || !passwordEncoder.matches(password, employeeDTO.getPassword())) {
            throw new BusinessException(ResultCodeEnum.LOGIN_FAILED);
        }
        // 登录
        AdminUserDetails userDetails = new AdminUserDetails(employeeDTO);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return employeeDTO;
    }

    @Override
    public EmployeeDTO getEmployeeById(String uid) {
        Employee employee = employeeMapper.selectByPrimaryKey(uid);
        if (employee == null) {
            return null;
        }
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        employeeDTO.setRole(RoleEnum.getRoleFromOrdinal(employee.getRole()));
        return employeeDTO;
    }

    @Override
    public String register(RegisterParam registerParam) {
        String id = UUID.fastUUID().toString();
        Employee ex = employeeMapper.selectOneByUsername(registerParam.getUsername());
        if (ObjectUtil.isNotNull(ex)) {
            throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "当前用户名已存在");
        }
        Employee employee = new Employee();
        Department dex = departmentMapper.selectByPrimaryKey(registerParam.getDepartmentId());
        if (ObjectUtil.isNull(dex)) {
            throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "指定部门不存在");
        }
        BeanUtils.copyProperties(registerParam, employee);
        employee.setId(id);
        employee.setRole(registerParam.getRole().ordinal());
        employeeMapper.insert(employee);
        return id;
    }
}




