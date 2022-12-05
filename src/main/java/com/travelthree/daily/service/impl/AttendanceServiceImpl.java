package com.travelthree.daily.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import com.travelthree.daily.constant.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travelthree.daily.domain.Attendance;
import com.travelthree.daily.domain.Employee;
import com.travelthree.daily.domain.Leave;
import com.travelthree.daily.dto.AttendanceParam;
import com.travelthree.daily.dto.PageParam;
import com.travelthree.daily.exception.BusinessException;
import com.travelthree.daily.mapper.AttendanceMapper;
import com.travelthree.daily.mapper.EmployeeMapper;
import com.travelthree.daily.mapper.LeaveMapper;
import com.travelthree.daily.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author faust
* @description 针对表【attendance】的数据库操作Service实现
* @createDate 2022-11-23 15:56:31
*/
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private LeaveMapper leaveMapper;

    @Override
    public void attend(String uid) {
        if (!checkTimeBetween(WebConstant.ATTENDANCE_START_TIME, WebConstant.ATTENDANCE_END_TIME, LocalDateTime.now())) {
            throw new BusinessException(ResultCodeEnum.FORBIDDEN_OP, "当前不在考勤时间点内");
        }
        // 更新当天的考勤状态
        Attendance attendance = Attendance.builder()
                .employeeId(uid).status(AttendanceStatus.WORK.ordinal()).date(LocalDate.now()).build();
        attendanceMapper.updateSelectiveByIdAndTime(attendance);
    }

    // 使用事务保证插入全部成功，同时减少数据库请求次数
    @Transactional
    @Override
    public void addAttend() {
        List<Employee> employees = employeeMapper.selectAllByRole(RoleEnum.STAFF.ordinal());
        // 所有请假的初始化为请假状态，没请假的初始化为缺勤状态
        Set<String> leaves = leaveMapper.selectAllByStatusAndDate(LeaveCheckStatus.APPROVE.ordinal(), LocalDate.now()).stream()
                .map(Leave::getEmployeeId).collect(Collectors.toSet());
        List<Attendance> attendances = employees.stream()
                .map(e -> Attendance.builder()
                        .id(UUID.fastUUID().toString())
                        .date(LocalDate.now())
                        .status(leaves.contains(e.getId()) ?
                                AttendanceStatus.LEAVE.ordinal() : AttendanceStatus.ABSENT.ordinal())
                        .employeeId(e.getId()).build()).toList();
        attendanceMapper.insertBatch(attendances);
    }

    private boolean checkTimeBetween(String start, String end, LocalDateTime now) {
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now(), LocalTime.parse(start));
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now(), LocalTime.parse(end));
        return now.isAfter(startTime) && now.isBefore(endTime);
    }

    @Override
    public PageInfo queryAttendanceByDate(AttendanceParam attendanceParam, PageParam pageParam) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getPageSize());
        if (attendanceParam.getStartDate().isAfter(attendanceParam.getEndDate())) {
            throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "开始日期应该晚于截止日期");
        }
        List<Attendance> attendances = attendanceMapper.selectByDate(attendanceParam);
        PageInfo pageInfo = new PageInfo(attendances);
        pageInfo.setPageSize(pageParam.getPageSize());
        return pageInfo;
    }

    @Override
    public List<Attendance> getAttendancesByEmployeeId(String employeeId) {

        return attendanceMapper.selectAllByEmployeeId(employeeId);
    }

//    @Override
//    public PageInfo<Attendance> getPageAttendanceByEmployeeId(String employeeId) {
//
//        List<Attendance> attendanceList = attendanceMapper.selectByEmployeeId(employeeId);
//        return new PageInfo<>(attendanceList);
//    }
}




