package com.travelthree.daily.service;

import com.travelthree.daily.domain.Employee;
import com.travelthree.daily.dto.ChangePwdParam;
import com.travelthree.daily.dto.EmployeeDTO;
import com.travelthree.daily.dto.RegisterParam;
import com.travelthree.daily.dto.UpdateEmployeeParam;

import java.util.List;

/**
* @author faust
* @description 针对表【employee】的数据库操作Service
* @createDate 2022-11-23 15:56:31
*/
public interface EmployeeService {

    /**
     * 根据名称获取员工信息
     * */
    EmployeeDTO getEmployeeByUsername(String username);

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 未加密的密码
     * @return 用户id
     */
    EmployeeDTO login(String username, String password);

    EmployeeDTO getEmployeeById(String uid);

    /** 用户注册
     * @param registerParam 注册参数
     * */
    String register(RegisterParam registerParam);

    /**
     * 更新用户信息
     * */
    void update(Employee employee);

    /**
     * 修改密码
     * */
    void changePassword(String id, ChangePwdParam param);

    List<Employee> queryEmployeeByPage(Integer pageNum,Integer pageSize);
}
