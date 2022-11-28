package com.travelthree.daily.service.impl;

import cn.hutool.core.lang.UUID;
import com.travelthree.daily.constant.AttendanceStatus;
import com.travelthree.daily.constant.RoleEnum;
import com.travelthree.daily.domain.Attendance;
import com.travelthree.daily.domain.Employee;
import com.travelthree.daily.domain.Leave;
import com.travelthree.daily.mapper.AttendanceMapper;
import com.travelthree.daily.mapper.EmployeeMapper;
import com.travelthree.daily.mapper.LeaveMapper;
import com.travelthree.daily.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        // 更新最新的一次考勤状态
        attendanceMapper.updateRecentStatusByTime(uid, AttendanceStatus.WORK.ordinal());
    }

    // 使用事务保证插入全部成功，同时减少数据库请求次数
    @Transactional
    @Override
    public void addAttend() {
        List<Employee> employees = employeeMapper.selectAllByRole(RoleEnum.STAFF.ordinal());
        // 所有请假的初始化为请假状态，没请假的初始化为缺勤状态
        Set<String> leaves = leaveMapper.selectAllByDate(LocalDate.now()).stream()
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
}




