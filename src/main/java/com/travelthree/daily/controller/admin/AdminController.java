package com.travelthree.daily.controller.admin;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.travelthree.daily.constant.LeaveCheckStatus;
import com.travelthree.daily.constant.ResultCodeEnum;
import com.travelthree.daily.constant.RoleEnum;
import com.travelthree.daily.cron.AttendanceSchedule;
import com.travelthree.daily.domain.*;
import com.travelthree.daily.dto.*;
import com.travelthree.daily.exception.BusinessException;
import com.travelthree.daily.service.*;
import com.travelthree.daily.utils.PageTransformUtil;
import com.travelthree.daily.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@Validated
@Controller
@RequestMapping("/api/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LeaveTypeService leaveTypeService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private AttendanceSchedule attendanceSchedule;

    @PostMapping("/register")
    @ResponseBody
    public String register(@Valid @RequestBody RegisterParam registerParam) {
        return employeeService.register(registerParam);
    }

    @GetMapping("/employee")
    @ResponseBody
    @Deprecated
    public PageInfo queryEmployee(@Valid PageParam pageParam) {
        PageInfo pageInfo = employeeService.queryEmployeeByPage(pageParam);
        pageInfo.setPageSize(pageParam.getPageSize());
        //取出pageInfo里面的List<Employee>
        List<Employee> employees1 = pageInfo.getList();
        //初始化接口要求的视图对象集合
        List<EmployeeVo> employeeVos = new LinkedList<>();
        for (Employee employee : employees1) {
            //每次都创建新的视图对象
            EmployeeVo employeeVo = new EmployeeVo();
            //拷贝属性
            BeanUtils.copyProperties(employee, employeeVo);
            //变更role的类型后拷贝
            employeeVo.setRole(RoleEnum.getRoleFromOrdinal(employee.getRole()));
            //通过employee的部门ID调用部门服务类查询部门名称并赋值
            String tmpDepartmentId = employee.getDepartmentId();
            Department department = departmentService.selectByPrimaryKey(tmpDepartmentId);
            employeeVo.setDepartment(department.getName());
            //在集合中添加
            employeeVos.add(employeeVo);
        }
        //将集合赋回pageInfo
        pageInfo.setList(employeeVos);
        return pageInfo;
    }

    @PutMapping("/employee/{id}")
    @ResponseBody
    public CommonResult updateInfo(@Valid @RequestBody UpdateEmployeeParam updateEmployeeParam, @PathVariable String id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        if (employeeDTO == null) {
            return CommonResult.failure("该用户id不存在");
        }
        Employee employee = new Employee();
        BeanUtils.copyProperties(updateEmployeeParam, employee);
        employee.setId(id);
        employeeService.update(employee);
        return CommonResult.success();
    }

    @DeleteMapping("/employee/{id}")
    @ResponseBody
    public CommonResult deleteInfo(@PathVariable String id) {
        employeeService.deleteInfo(id);
        return CommonResult.success();
    }

    @PostMapping("/leave-status")
    @ResponseBody
    public CommonResult addLeaveType(@Valid @RequestBody LeaveType leaveType) {
        leaveTypeService.addLeaveType(leaveType.getName());
        return CommonResult.success();
    }

    @PutMapping("/leave-status/{id}")
    @ResponseBody
    public CommonResult updateLType(@Valid @RequestBody LeaveType leaveType, @PathVariable Integer id) {
        leaveTypeService.updateLeaveType(leaveType, id);
        return CommonResult.success();
    }

    @DeleteMapping("/leave-status/{id}")
    @ResponseBody
    public CommonResult deleteInfoLt(@PathVariable Integer id) {
        leaveTypeService.deleteInfo(id);
        return CommonResult.success();
    }

    @PostMapping("/department")
    @ResponseBody
    public CommonResult addInfoD(@Valid @RequestBody Department department) {
        departmentService.addDepartment(department);
        return CommonResult.success();
    }

    @PutMapping("/department/{id}")
    @ResponseBody
    public CommonResult updateDType(@Valid @RequestBody Department department, @PathVariable String id) {
        departmentService.updateDepartment(department, id);
        return CommonResult.success();
    }

    @DeleteMapping("/department/{id}")
    @ResponseBody
    public CommonResult deleteInfoD(@PathVariable String id) {
        departmentService.deleteInfo(id);
        return CommonResult.success();
    }

    @GetMapping("/attendance")
    @ResponseBody
    @Deprecated
    public PageInfo getAttendance(@Valid AttendanceParam attendanceParam,@Valid PageParam pageParam) {
        PageInfo pageInfo = attendanceService.queryAttendanceByDate(attendanceParam, pageParam);

        //取出pageInfo里面的List<Employee>
        List<Attendance> attendances = pageInfo.getList();
        //初始化接口要求的视图对象集合
        List<AttendanceVo> attendanceVos= new LinkedList<>();
        for (Attendance attendance : attendances) {
            //每次都创建新的视图对象
            AttendanceVo attendanceVo = new AttendanceVo();
            //拷贝属性
            BeanUtils.copyProperties(attendance, attendanceVo);
            //变更Status和LocalDate的类型后拷贝
            attendanceVo.setStatus(attendance.getStatus().toString());
            attendanceVo.setDate(attendance.getDate().toString());
            //通过Attendance的员工ID调用员工服务类查询员工名称并赋值
            String employeeId = attendance.getEmployeeId();
            EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);
            attendanceVo.setEmployeeName(employeeDTO.getName());
            //在集合中添加
            attendanceVos.add(attendanceVo);
        }
        //将集合赋回pageInfo
        pageInfo.setList(attendanceVos);
        return pageInfo;
    }

    @PutMapping("/leave/check/{id}")
    @ResponseBody
    public CommonResult checkLeave(@Valid @RequestBody LeaveStatusParam param, @PathVariable String id) {
        Leave leave = leaveService.getById(id);
        if(ObjectUtil.isNull(leave)) {
            throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "该请假id不存在");
        }
        if(param.getStatus().toString().equals(LeaveCheckStatus.APPROVE.toString())) {
            //判定是否需要修改
            if(leave.getStatus() == LeaveCheckStatus.APPROVE.ordinal()) {
                throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "无需更改的结果");
            }
            //更改请假状态为通过
            leave.setStatus(LeaveCheckStatus.APPROVE.ordinal());
            leaveService.updateLeaveStatus(leave);
            return CommonResult.success();
        }
        if(param.getStatus().toString().equals(LeaveCheckStatus.REFUSE.toString())) {
            //判定是否需要修改
            if(leave.getStatus() == LeaveCheckStatus.REFUSE.ordinal()) {
                throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "无需更改的结果");
            }
            //更改请假状态为不通过
            leave.setStatus(LeaveCheckStatus.REFUSE.ordinal());
            leaveService.updateLeaveStatus(leave);
            return CommonResult.success();
        }
        else {
            throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "请输入有效的审核结果");
        }
    }

    @GetMapping("/leave")
    @ResponseBody
    @Deprecated
    public PageInfo getLeaveRequest(@Valid PageParam pageParam, LeaveCheckStatus leaveType) {
            PageInfo pageInfo = leaveService.queryLeave(pageParam, leaveType);
            if(pageParam.getPage() != null) {
                pageInfo.setPageNum(pageParam.getPage());
            }
            if(pageParam.getPageSize() != null) {
                pageInfo.setPageSize(pageParam.getPageSize());
            }
            //取出pageInfo里面的List<Leave>
            List<Leave> leaves = pageInfo.getList();
            //初始化接口要求的视图对象集合
            List<LeaveVo> leaveRequestVos= new LinkedList<>();
            for (Leave leaf : leaves) {
                //每次都创建新的视图对象
                LeaveVo leaveRequestVo = new LeaveVo();
                //拷贝属性
                BeanUtils.copyProperties(leaf, leaveRequestVo);
                //通过leave的员工ID调用员工服务类查询员工名称并赋值
                String employeeId = leaf.getEmployeeId();
                EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);
                leaveRequestVo.setName(employeeDTO.getName());
                //将typeId转换为type后赋值
                leaveRequestVo.setType(leaveTypeService.selectById(leaf.getTypeId()).getName());
                //将status装换后赋值
                leaveRequestVo.setStatus(leaveType.toString());
                //在集合中添加
                leaveRequestVos.add(leaveRequestVo);
            }
            //将集合赋回pageInfo
            pageInfo.setList(leaveRequestVos);
            return pageInfo;
    }

    @GetMapping("/leave-status")
    @ResponseBody
    public PageInfo<LeaveTypeVo> getLeaveTypes(@Valid PageParam pageParam) {

        return PageTransformUtil.toViewPage(pageParam,
                () -> leaveTypeService.getAllLeaveTypes(),
                leaveType -> new LeaveTypeVo(leaveType.getId().toString(), leaveType.getName()));
    }

    @GetMapping("/department")
    @ResponseBody
    public PageInfo<DepartmentVo> getDepartments(@Valid PageParam pageParam) {

        return PageTransformUtil.toViewPage(pageParam,
                () -> departmentService.selectAllDepartments(),
                department -> new DepartmentVo(
                        department.getId(),
                        department.getName(),
                        departmentService.getSuperiorName(department)
                ));
    }

    @PutMapping("/attendance/auto")
    @ResponseBody
    public void updateAutoAttendanceStatus(@RequestBody Boolean enable) {
        if (enable) {
            attendanceSchedule.enable();
        } else {
            attendanceSchedule.disable();
        }
    }

    @PostMapping("/attendance")
    public void createAttendance() {
        attendanceService.addAttend();
    }
}
