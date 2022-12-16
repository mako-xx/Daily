package com.travelthree.daily.controller.view;

import com.travelthree.daily.domain.LeaveType;
import com.travelthree.daily.dto.EmployeeDTO;
import com.travelthree.daily.service.AttendanceService;
import com.travelthree.daily.service.LeaveService;
import com.travelthree.daily.service.LeaveTypeService;
import com.travelthree.daily.utils.TaleUtil;
import com.travelthree.daily.vo.LeaveVo;
import com.travelthree.daily.vo.SelfAttendanceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeViewController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private LeaveTypeService leaveTypeService;

    // 员工打卡记录页面
    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        EmployeeDTO currentLoginUser = TaleUtil.getCurrentLoginUser(request);
        List<SelfAttendanceVo> list = attendanceService.getEmployeeAttendance(currentLoginUser.getId());
        request.setAttribute("list", list);
        return "employee/checkin";
    }

    @GetMapping("/leave")
    public String leave(HttpServletRequest request) {
        EmployeeDTO currentLoginUser = TaleUtil.getCurrentLoginUser(request);
        List<LeaveVo> list = leaveService.getEmployeeLeaves(currentLoginUser.getId(), currentLoginUser.getName());
        List<LeaveType> leaveTypes = leaveTypeService.getAllLeaveTypes();
        request.setAttribute("list", list);
        request.setAttribute("leaveTypes", leaveTypes);
        return "employee/leave";
    }


}
