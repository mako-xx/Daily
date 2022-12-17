package com.travelthree.daily.controller;

import com.github.pagehelper.PageInfo;
import com.travelthree.daily.config.converter.LocalDateConverter;
import com.travelthree.daily.domain.Employee;
import com.travelthree.daily.dto.*;
import com.travelthree.daily.service.AttendanceService;
import com.travelthree.daily.service.EmployeeService;
import com.travelthree.daily.service.LeaveService;
import com.travelthree.daily.service.LeaveTypeService;
import com.travelthree.daily.utils.PageTransformUtil;
import com.travelthree.daily.utils.TaleUtil;
import com.travelthree.daily.vo.CommonResult;
import com.travelthree.daily.vo.LeaveVo;
import com.travelthree.daily.vo.SelfAttendanceVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author 陈宣辰
 * @date 2022-11-23 16:57:37
 * @description
 */
@Validated
@Controller
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private LeaveTypeService leaveTypeService;

    @PutMapping("/update")
    @ResponseBody
    public CommonResult update(@Valid @RequestBody UpdateEmployeeParam param, HttpServletRequest request) {
        // 禁止该接口更新用户角色
        param.setRole(null);
        EmployeeDTO currentLoginUser = TaleUtil.getCurrentLoginUser(request);
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
        String userId = currentLoginUser.getId();
        leaveService.addLeave(param, userId);
        return CommonResult.success();
    }

    @GetMapping("/leave")
    @ResponseBody
    public PageInfo<LeaveVo> getLeaveHistory(@Valid PageParam pageParam, HttpServletRequest request) {

        EmployeeDTO currentLoginUser = TaleUtil.getCurrentLoginUser(request);

        return PageTransformUtil.toViewPage(pageParam,
                () -> leaveService.getLeavesByEmployeeId(currentLoginUser.getId()),
                leave -> new LeaveVo(
                        leave.getId(),
                        leave.getStartdate().toString(),
                        leave.getEnddate().toString(),
                        leave.getStatus().toString(),
                        leaveTypeService.selectById(leave.getTypeId()).getName(),
                        leave.getReason(),
                        leave.getEmployeeId(),
                        currentLoginUser.getName()
                ));
    }

    @GetMapping("/attendance")
    @ResponseBody
    public PageInfo<SelfAttendanceVo> getAttendanceRecord(@Valid PageParam pageParam, HttpServletRequest request) {

        EmployeeDTO currentLoginUser = TaleUtil.getCurrentLoginUser(request);

        return PageTransformUtil.toViewPage(pageParam,
                () -> attendanceService.getAttendancesByEmployeeId(currentLoginUser.getId()),
                attendance -> new SelfAttendanceVo(
                        attendance.getId(),
                        attendance.getDate().toString(),
                        attendance.getStatus().toString()));
    }
}
