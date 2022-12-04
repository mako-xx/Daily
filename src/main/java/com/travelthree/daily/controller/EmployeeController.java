package com.travelthree.daily.controller;

import com.travelthree.daily.domain.Employee;
import com.travelthree.daily.dto.AskForLeaveParam;
import com.travelthree.daily.dto.ChangePwdParam;
import com.travelthree.daily.dto.EmployeeDTO;
import com.travelthree.daily.dto.UpdateEmployeeParam;
import com.travelthree.daily.service.AttendanceService;
import com.travelthree.daily.service.EmployeeService;
import com.travelthree.daily.service.LeaveService;
import com.travelthree.daily.utils.TaleUtil;
import com.travelthree.daily.vo.CommonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author 陈宣辰
 * @date 2022-11-23 16:57:37
 * @description
 */
@Controller
@RequestMapping("/api/employee")
public class EmployeeController {

    public static final String USER_NOT_LOGIN = "当前用户未登录";

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private LeaveService leaveService;

    @PutMapping("/update")
    @ResponseBody
    public CommonResult update(@Valid @RequestBody UpdateEmployeeParam param, HttpServletRequest request) {
        // 禁止该接口更新用户角色
        param.setRole(null);
        EmployeeDTO currentLoginUser = TaleUtil.getCurrentLoginUser(request);
        if (currentLoginUser == null) {
            return CommonResult.failure(USER_NOT_LOGIN);
        }
        Employee employee = new Employee();
        BeanUtils.copyProperties(param, employee);
        employee.setId(currentLoginUser.getId());
        employeeService.update(employee);
        return CommonResult.success();
    }

    @PutMapping("/update/password")
    @ResponseBody
    public CommonResult changePassword(@RequestBody @Valid ChangePwdParam param, HttpServletRequest request, HttpServletResponse response) {
        EmployeeDTO currentLoginUser = TaleUtil.getCurrentLoginUser(request);
        if (currentLoginUser == null) {
            return CommonResult.failure(USER_NOT_LOGIN);
        }
        employeeService.changePassword(currentLoginUser.getId(), param);
        TaleUtil.logout(request, response);
        return CommonResult.success();
    }

    @PostMapping("/attend")
    @ResponseBody
    public CommonResult attend(HttpServletRequest request) {
        attendanceService.attend(TaleUtil.getCurrentLoginUser(request).getId());
        return CommonResult.success();
    }

    @PostMapping("/leave")
    @ResponseBody
    public CommonResult askForLeave(@Valid @RequestBody AskForLeaveParam param, HttpServletRequest request, HttpServletResponse response) {

        EmployeeDTO currentLoginUser = TaleUtil.getCurrentLoginUser(request);
        if (currentLoginUser == null) {
            return CommonResult.failure(USER_NOT_LOGIN);
        }
        String userId = currentLoginUser.getId();
        leaveService.addLeave(param, userId);
        TaleUtil.logout(request, response);
        return CommonResult.success();
    }
}
