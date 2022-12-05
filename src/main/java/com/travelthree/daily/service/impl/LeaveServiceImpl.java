package com.travelthree.daily.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travelthree.daily.domain.Leave;
import com.travelthree.daily.dto.PageParam;
import com.travelthree.daily.mapper.LeaveMapper;
import cn.hutool.core.lang.UUID;
import com.travelthree.daily.constant.ResultCodeEnum;
import com.travelthree.daily.dto.AskForLeaveParam;
import com.travelthree.daily.exception.BusinessException;
import com.travelthree.daily.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.sql.Date;

/**
* @author faust
* @description 针对表【leave】的数据库操作Service实现
* @createDate 2022-11-27 16:45:35
*/
@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveMapper leaveMapper;

    @Override
    public Leave getById(String id) {
        return leaveMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateLeaveStatus(Leave leave) {
        leaveMapper.updateByPrimaryKeySelective(leave);
    }

    @Override
    public PageInfo queryLeave(PageParam pageParam, Integer status) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getPageSize());
        List<Leave> leaves = leaveMapper.queryLeave(status);
        PageInfo pageInfo = new PageInfo(leaves);
        pageInfo.setPageSize(pageParam.getPageSize());
        return pageInfo;
    }

    public List<Leave> getLeavesByEmployeeId(String employeeId) {

        return leaveMapper.selectAllByEmployeeId(employeeId);
    }

    @Override
    public PageInfo<Leave> getPageLeaveHistory(String employeeId) {

        List<Leave> leaveList = leaveMapper.selectAllByEmployeeId(employeeId);
        return new PageInfo<>(leaveList);
    }

    @Override
    public void addLeave(AskForLeaveParam param, String employeeId) {

        Date start = Date.valueOf(param.getStartDate());
        Date end = Date.valueOf(param.getEndDate());
        if (start.after(end)) {
            throw new BusinessException(ResultCodeEnum.FORBIDDEN_OP, "起始时间不得晚于结束时间");
        }

        List<Leave> leaveList = leaveMapper.selectAllByEmployeeId(employeeId);
        if (leaveList.stream().anyMatch(leave ->
                (start.before(leave.getStartdate())
                        && end.after(leave.getStartdate()))
                || (start.before(leave.getEnddate())
                        && end.after(leave.getEnddate()))

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




