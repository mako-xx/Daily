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
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@Validated
@Controller
@RequestMapping("/api/admin")
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
    public PageInfo queryEmployee(@Valid PageParam pageParam) {
        PageInfo pageInfo = employeeService.queryEmployeeByPage(pageParam);
        pageInfo.setPageSize(pageParam.getPageSize());
        //接下来将查询来的部门id转化为部门名称，联表查询彻底失败，我是菜逼
        //取出pageInfo里面的List<Employee>
        List<Employee> employees1 = pageInfo.getList();
        //初始化接口要求的视图对象集合
        List<EmployeeVo> employeeVos = new LinkedList<>();
        //菜逼的for循环赋值环节
        for(int tmp = 0; tmp < employees1.size(); tmp++) {
            //每次都创建新的视图对象
            EmployeeVo employeeVo = new EmployeeVo();
            //拷贝属性
            BeanUtils.copyProperties(employees1.get(tmp), employeeVo);
            //变更role的类型后拷贝
            employeeVo.setRole(RoleEnum.getRoleFromOrdinal(employees1.get(tmp).getRole()));
            //通过employee的部门ID调用部门服务类查询部门名称并赋值
            String tmpDepartmentId = employees1.get(tmp).getDepartmentId();
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
    public PageInfo getAttendance(@Valid AttendanceParam attendanceParam,@Valid PageParam pageParam) {
        PageInfo pageInfo = attendanceService.queryAttendanceByDate(attendanceParam, pageParam);

        //取出pageInfo里面的List<Employee>
        List<Attendance> attendances = pageInfo.getList();
        //初始化接口要求的视图对象集合
        List<AttendanceVo> attendanceVos= new LinkedList<>();
        //菜逼的for循环赋值环节
        for(int tmp = 0; tmp < attendances.size(); tmp++) {
            //每次都创建新的视图对象
            AttendanceVo attendanceVo = new AttendanceVo();
            //拷贝属性
            BeanUtils.copyProperties(attendances.get(tmp), attendanceVo);
            //变更Status和LocalDate的类型后拷贝
            attendanceVo.setStatus(attendances.get(tmp).getStatus().toString());
            attendanceVo.setDate(attendances.get(tmp).getDate().toString());
            //通过Attendance的员工ID调用员工服务类查询员工名称并赋值
            String employeeId = attendances.get(tmp).getEmployeeId();
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
//            //找到请假员工id
//            String employeeID = leave.getEmployeeId();
//            //找到请假员工的出勤信息
//            Attendance attendance = attendanceService.getByEmployeeId(employeeID);
//            //更改员工考勤为请假
//            attendance.setStatus(2);
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
    public PageInfo getLeaveRequest(@Valid PageParam pageParam, LeaveCheckStatus leaveType) {
            PageInfo pageInfo = leaveService.queryLeave(pageParam, leaveType.ordinal());
            if(pageParam.getPage() != null) {
                pageInfo.setPageNum(pageParam.getPage());
            }
            if(pageParam.getPageSize() != null) {
                pageInfo.setPageSize(pageParam.getPageSize());
            }
            //取出pageInfo里面的List<Leave>
            List<Leave> leaves = pageInfo.getList();
            //初始化接口要求的视图对象集合
            List<LeaveRequestVo> leaveRequestVos= new LinkedList<>();
            //菜逼的for循环赋值环节
            for(int tmp = 0; tmp < leaves.size(); tmp++) {
                //每次都创建新的视图对象
                LeaveRequestVo leaveRequestVo = new LeaveRequestVo();
                //拷贝属性
                BeanUtils.copyProperties(leaves.get(tmp), leaveRequestVo);
//                //变更日期类型后拷贝
//                leaveRequestVo.setStartDate(leaves.get(tmp).getStartdate().toString());
//                leaveRequestVo.setEndDate(leaves.get(tmp).getEnddate().toString());
                //通过leave的员工ID调用员工服务类查询员工名称并赋值
                String employeeId = leaves.get(tmp).getEmployeeId();
                EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);
                leaveRequestVo.setName(employeeDTO.getName());
                //将typeId转换为type后赋值
                leaveRequestVo.setType(leaveTypeService.selectById(leaves.get(tmp).getTypeId()).getName());
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
