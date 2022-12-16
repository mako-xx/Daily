package com.travelthree.daily.controller.view;

import cn.hutool.core.util.StrUtil;
import com.travelthree.daily.constant.LeaveCheckStatus;
import com.travelthree.daily.domain.Department;
import com.travelthree.daily.domain.Leave;
import com.travelthree.daily.domain.LeaveType;
import com.travelthree.daily.dto.AttendanceParam;
import com.travelthree.daily.dto.EmployeeDTO;
import com.travelthree.daily.dto.PageParam;
import com.travelthree.daily.service.*;
import com.travelthree.daily.vo.AttendanceVo;
import com.travelthree.daily.vo.DepartmentVo;
import com.travelthree.daily.vo.EmployeeVo;
import com.travelthree.daily.vo.LeaveVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminViewController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LeaveTypeService leaveTypeService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/employees")
    public String showEmployees(HttpServletRequest request, @RequestParam(required = false) String id) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        EmployeeVo employeeVo = new EmployeeVo();
        if (employee != null) {
            BeanUtils.copyProperties(employee, employeeVo);
            employeeVo.setDepartment(departmentService.selectByPrimaryKey(employee.getDepartmentId()).getName());
        }
        List<EmployeeVo> list = employeeService.getAllEmployee();
        List<Department> departments = departmentService.selectAllDepartments();
        request.setAttribute("list", list);
        request.setAttribute("departments", departments);
        request.setAttribute("employee", employeeVo);
        return "admin/employees";
    }

    @GetMapping("/departments")
    public String showDepartments(HttpServletRequest request) {
        List<Department> departments = departmentService.selectAllDepartments();
        List<DepartmentVo> departmentList = departments.stream()
                .map(d -> new DepartmentVo(
                        d.getId(),
                        d.getName(),
                        departmentService.getSuperiorName(d)
                )).toList();
        request.setAttribute("departments", departmentList);
        return "admin/departments";
    }

    @GetMapping("/leavetypes")
    public String showLeaveType(HttpServletRequest request) {
        List<LeaveType> leaveTypes = leaveTypeService.getAllLeaveTypes();
        request.setAttribute("leaveTypes", leaveTypes);
        return "admin/leavetypes";
    }

    @GetMapping("/checkins")
    public String showCheckIn(HttpServletRequest request, @Valid AttendanceParam attendanceParam) {
        List<AttendanceVo> list = attendanceService.getAttendanceList(attendanceParam);
        request.setAttribute("list", list);
        return "admin/checkins";
    }

    @GetMapping("/leaves")
    public String showLeaves(HttpServletRequest request,
                             @RequestParam(defaultValue = "WAITING") LeaveCheckStatus leaveStatus,
                             @RequestParam(required = false) String id) {
        List<LeaveVo> list = leaveService.queryLeaveList(leaveStatus);
        LeaveVo leave = null;
        if (StrUtil.isNotBlank(id)) {
            leave = leaveService.getVoById(id);
            leave.setStatus(leaveStatus.toString());
        }
        request.setAttribute("list", list);
        request.setAttribute("leave", leave);
        return "admin/checkleaves";
    }
}
