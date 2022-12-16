package com.travelthree.daily.service.impl;

import cn.hutool.core.lang.UUID;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travelthree.daily.constant.LeaveCheckStatus;
import com.travelthree.daily.constant.ResultCodeEnum;
import com.travelthree.daily.domain.Employee;
import com.travelthree.daily.domain.Leave;
import com.travelthree.daily.dto.AskForLeaveParam;
import com.travelthree.daily.dto.PageParam;
import com.travelthree.daily.exception.BusinessException;
import com.travelthree.daily.mapper.EmployeeMapper;
import com.travelthree.daily.mapper.LeaveMapper;
import com.travelthree.daily.mapper.LeaveTypeMapper;
import com.travelthree.daily.service.LeaveService;
import com.travelthree.daily.vo.LeaveVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author faust
* @description 针对表【leave】的数据库操作Service实现
* @createDate 2022-11-27 16:45:35
*/
@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveMapper leaveMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private LeaveTypeMapper leaveTypeMapper;

    @Override
    public Leave getById(String id) {
        return leaveMapper.selectByPrimaryKey(id);
    }

    @Override
    public LeaveVo getVoById(String id) {
        Leave leave = leaveMapper.selectByPrimaryKey(id);
        LeaveVo leaveVo = new LeaveVo();
        BeanUtils.copyProperties(leave, leaveVo);
        Employee employee = employeeMapper.selectByPrimaryKey(leave.getEmployeeId());
        leaveVo.setStartDate(leave.getStartdate().toString());
        leaveVo.setEndDate(leave.getEnddate().toString());
        leaveVo.setName(employee.getName());
        leaveVo.setType(leaveTypeMapper.selectByPrimaryKey(leave.getTypeId()).getName());
        return leaveVo;
    }

    @Override
    public void updateLeaveStatus(Leave leave) {
        leaveMapper.updateByPrimaryKeySelective(leave);
    }

    @Override
    public PageInfo<LeaveVo> queryLeave(PageParam pageParam, LeaveCheckStatus status) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getPageSize());
        List<Leave> leaveList = leaveMapper.queryLeave(status.ordinal());
        PageInfo<Leave> pageInfo = new PageInfo<>(leaveList);
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
            Employee employee = employeeMapper.selectByPrimaryKey(employeeId);
            leaveRequestVo.setName(employee.getName());
            //将typeId转换为type后赋值
            leaveRequestVo.setType(leaveTypeMapper.selectByPrimaryKey(leaf.getTypeId()).getName());
            //将status装换后赋值
            leaveRequestVo.setStatus(status.toString());
            //在集合中添加
            leaveRequestVos.add(leaveRequestVo);
        }
        PageInfo<LeaveVo> page = new PageInfo<>(leaveRequestVos);
        BeanUtils.copyProperties(pageInfo, page);
        page.setList(leaveRequestVos);
        return page;
    }

    @Override
    public List<LeaveVo> queryLeaveList(LeaveCheckStatus status) {
        List<Leave> leaveList = leaveMapper.queryLeave(status.ordinal());
        return leaveList.stream()
                .map(leave -> {
                    //每次都创建新的视图对象
                    LeaveVo leaveRequestVo = new LeaveVo();
                    //拷贝属性
                    BeanUtils.copyProperties(leave, leaveRequestVo);
                    //通过leave的员工ID调用员工服务类查询员工名称并赋值
                    String employeeId = leave.getEmployeeId();
                    Employee employee = employeeMapper.selectByPrimaryKey(employeeId);
                    leaveRequestVo.setName(employee.getName());
                    //将typeId转换为type后赋值
                    leaveRequestVo.setType(leaveTypeMapper.selectByPrimaryKey(leave.getTypeId()).getName());
                    //将status装换后赋值
                    leaveRequestVo.setStatus(status.toString());
                    return leaveRequestVo;
                }).toList();
    }

    public List<Leave> getLeavesByEmployeeId(String employeeId) {

        return leaveMapper.selectAllByEmployeeId(employeeId);
    }

    @Override
    public List<LeaveVo> getEmployeeLeaves(String employeeId, String username) {
        List<Leave> leaves = leaveMapper.selectAllByEmployeeId(employeeId);
        return leaves.stream()
                .map(leave -> new LeaveVo(
                        leave.getId(),
                        leave.getStartdate().toString(),
                        leave.getEnddate().toString(),
                        leave.getStatus().toString(),
                        leaveTypeMapper.selectByPrimaryKey(leave.getTypeId()).getName(),
                        leave.getReason(),
                        leave.getEmployeeId(),
                        username
                )).collect(Collectors.toList());
    }

    @Override
    public PageInfo<Leave> getPageLeaveHistory(String employeeId) {

        List<Leave> leaveList = leaveMapper.selectAllByEmployeeId(employeeId);
        return new PageInfo<>(leaveList);
    }

    @Override
    public void addLeave(AskForLeaveParam param, String employeeId) {

        LocalDate start = param.getStartDate();
        LocalDate end = param.getEndDate();
        if (start.isAfter(end)) {
            throw new BusinessException(ResultCodeEnum.FORBIDDEN_OP, "起始时间不得晚于结束时间");
        }

        List<Leave> leaveList = leaveMapper.selectAllByEmployeeId(employeeId);
        if (leaveList.stream().anyMatch(leave ->
                (start.isBefore(leave.getStartdate())
                        && end.isAfter(leave.getStartdate()))
                || (start.isBefore(leave.getEnddate())
                        && end.isAfter(leave.getEnddate()))

        )) {
            throw new BusinessException(ResultCodeEnum.FORBIDDEN_OP, "当前时段内用户已有请假");
        }

        Leave newLeave = new Leave();
        newLeave.setId(UUID.fastUUID().toString());
        newLeave.setEmployeeId(employeeId);
        newLeave.setStartdate(start);
        newLeave.setEnddate(end);
        newLeave.setStatus(0);
        newLeave.setTypeId(param.getType());
        newLeave.setReason(param.getReason());
        newLeave.setAuditorid("");

        leaveMapper.insert(newLeave);
    }
}




