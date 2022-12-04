package com.travelthree.daily.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travelthree.daily.domain.Employee;
import com.travelthree.daily.domain.Leave;
import com.travelthree.daily.dto.PageParam;
import com.travelthree.daily.mapper.LeaveMapper;
import com.travelthree.daily.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}




