package com.travelthree.daily.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travelthree.daily.constant.AttendanceStatus;
import com.travelthree.daily.constant.ResultCodeEnum;
import com.travelthree.daily.constant.RoleEnum;
import com.travelthree.daily.domain.Attendance;
import com.travelthree.daily.domain.Department;
import com.travelthree.daily.domain.Employee;
import com.travelthree.daily.dto.*;
import com.travelthree.daily.exception.BusinessException;
import com.travelthree.daily.mapper.AttendanceMapper;
import com.travelthree.daily.mapper.DepartmentMapper;
import com.travelthree.daily.mapper.EmployeeMapper;
import com.travelthree.daily.mapper.LeaveMapper;
import com.travelthree.daily.service.EmployeeService;
import com.travelthree.daily.vo.EmployeeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private LeaveMapper leaveMapper;

    @Autowired
    private AttendanceMapper attendanceMapper;

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
    @Transactional
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
        employee.setPassword(passwordEncoder.encode(registerParam.getPassword()));
        employee.setRole(registerParam.getRole().ordinal());
        employeeMapper.insert(employee);
        Attendance attendance = Attendance.builder()
                .id(UUID.fastUUID().toString())
                .employeeId(id)
                .date(LocalDate.now())
                .status(AttendanceStatus.ABSENT.ordinal())
                .build();
        attendanceMapper.insertSelective(attendance);
        return id;
    }

    @Override
    public void update(Employee employee) {
        Department dex = departmentMapper.selectByPrimaryKey(employee.getDepartmentId());
        if (ObjectUtil.isNull(dex)) {
            throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "该部门不存在");
        }
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    @Override
    public void changePassword(String id, ChangePwdParam param) {
        EmployeeDTO em = getEmployeeById(id);
        if (!passwordEncoder.matches(param.getOldPassword(), em.getPassword())) {
            throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "旧密码错误");
        }
        Employee employee = new Employee();
        employee.setPassword(passwordEncoder.encode(param.getNewPassword()));
        employee.setId(id);
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    @Override
    public PageInfo<EmployeeVo> queryEmployeeByPage(PageParam pageParam){
        PageHelper.startPage(pageParam.getPage(), pageParam.getPageSize());
        List<Employee> employeeList = employeeMapper.queryEmployeeByPage();
        PageInfo<Employee> pageInfo = new PageInfo<>(employeeList);
        //取出pageInfo里面的List<Employee>
        List<Employee> employees = pageInfo.getList();
        //初始化接口要求的视图对象集合
        List<EmployeeVo> employeeVos = new LinkedList<>();
        for (Employee employee : employees) {
            //每次都创建新的视图对象
            EmployeeVo employeeVo = new EmployeeVo();
            //拷贝属性
            BeanUtils.copyProperties(employee, employeeVo);
            //变更role的类型后拷贝
            employeeVo.setRole(RoleEnum.getRoleFromOrdinal(employee.getRole()));
            //通过employee的部门ID调用部门服务类查询部门名称并赋值
            String tmpDepartmentId = employee.getDepartmentId();
            Department department = departmentMapper.selectByPrimaryKey(tmpDepartmentId);
            employeeVo.setDepartment(department.getName());
            //在集合中添加
            employeeVos.add(employeeVo);
        }
        //将集合赋回pageInfo
        PageInfo<EmployeeVo> page = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, page);
        page.setList(employeeVos);
        return page;
    }

    @Override
    public void deleteInfo(String id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        if (employee == null) {
            throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "该用户id不存在");
        }
        attendanceMapper.deleteByEmployeeId(id);
        leaveMapper.deleteByEmployeeId(id);
        employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<EmployeeVo> getAllEmployee() {
        List<Employee> employees = employeeMapper.selectAllByRole(RoleEnum.STAFF.ordinal());
        return employees.stream()
                .map(employee -> {
                    //每次都创建新的视图对象
                    EmployeeVo employeeVo = new EmployeeVo();
                    //拷贝属性
                    BeanUtils.copyProperties(employee, employeeVo);
                    //变更role的类型后拷贝
                    employeeVo.setRole(RoleEnum.getRoleFromOrdinal(employee.getRole()));
                    //通过employee的部门ID调用部门服务类查询部门名称并赋值
                    String tmpDepartmentId = employee.getDepartmentId();
                    Department department = departmentMapper.selectByPrimaryKey(tmpDepartmentId);
                    employeeVo.setDepartment(department.getName());
                    return employeeVo;
                }).collect(Collectors.toList());
    }
}




