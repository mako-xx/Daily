package com.travelthree.daily.controller.view;

import com.github.pagehelper.PageInfo;
import com.travelthree.daily.domain.LeaveType;
import com.travelthree.daily.dto.EmployeeDTO;
import com.travelthree.daily.dto.PageParam;
import com.travelthree.daily.service.AttendanceService;
import com.travelthree.daily.service.LeaveService;
import com.travelthree.daily.service.LeaveTypeService;
import com.travelthree.daily.utils.PageTransformUtil;
import com.travelthree.daily.utils.TaleUtil;
import com.travelthree.daily.vo.LeaveVo;
import com.travelthree.daily.vo.SelfAttendanceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
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
    public String check(HttpServletRequest request, @Valid PageParam pageParam) {
        EmployeeDTO currentLoginUser = TaleUtil.getCurrentLoginUser(request);
        PageInfo<SelfAttendanceVo> page = PageTransformUtil.toViewPage(pageParam,
                () -> attendanceService.getAttendancesByEmployeeId(currentLoginUser.getId()),
                attendance -> new SelfAttendanceVo(
                        attendance.getId(),
                        attendance.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
                        attendance.getStatus().toString()));
        request.setAttribute("page", page);
        return "employee/checkin";
    }

    @GetMapping("/leave")
    public String leave(HttpServletRequest request, @Valid PageParam pageParam) {
        EmployeeDTO currentLoginUser = TaleUtil.getCurrentLoginUser(request);

        PageInfo<LeaveVo> page = PageTransformUtil.toViewPage(pageParam,
                () -> leaveService.getLeavesByEmployeeId(currentLoginUser.getId()),
                leave -> new LeaveVo(
                        leave.getId(),
                        DateFormat.getDateInstance().format(leave.getStartdate()),
                        DateFormat.getDateInstance().format(leave.getEnddate()),
                        leave.getStatus().toString(),
                        leaveTypeService.selectById(leave.getTypeId()).getName(),
                        leave.getReason(),
                        leave.getEmployeeId(),
                        currentLoginUser.getName()
                ));
        List<LeaveType> leaveTypes = leaveTypeService.getAllLeaveTypes();
        request.setAttribute("page", page);
        request.setAttribute("leaveTypes", leaveTypes);
        return "employee/leave";
    }


}
