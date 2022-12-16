package com.travelthree.daily.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.travelthree.daily.constant.ResultCodeEnum;
import com.travelthree.daily.domain.Leave;
import com.travelthree.daily.domain.LeaveType;
import com.travelthree.daily.exception.BusinessException;
import com.travelthree.daily.mapper.LeaveMapper;
import com.travelthree.daily.mapper.LeaveTypeMapper;
import com.travelthree.daily.service.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author faust
* @description 针对表【leave_type】的数据库操作Service实现
* @createDate 2022-11-27 16:45:35
*/
@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

    @Autowired
    LeaveTypeMapper leaveTypeMapper;

    @Autowired
    private LeaveMapper leaveMapper;

    /**
     * 获取所有请假类型
     *
     * @return 请假类型列表
     */
    @Override
    public List<LeaveType> getAllLeaveTypes() {

        return leaveTypeMapper.selectAll();
    }

    @Override
    public void addLeaveType(String name) {
        LeaveType lt = leaveTypeMapper.selectByName(name);
        if (ObjectUtil.isNotNull(lt)) {
            throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "当前请假类型已存在");
        }
        LeaveType leaveType = new LeaveType();
        leaveType.setName(name);
        leaveTypeMapper.insertSelective(leaveType);
    }

    @Override
    public void updateLeaveType(LeaveType leaveType, Integer id) {
        LeaveType lt = leaveTypeMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(lt)) {
            throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "当前请假类型id不存在");
        }
        lt.setName(leaveType.getName());
        leaveTypeMapper.updateByPrimaryKey(lt);
    }

    @Override
    public void deleteInfo(Integer id) {
        LeaveType lt = leaveTypeMapper.selectByPrimaryKey(id);
        List<Leave> leaves = leaveMapper.selectByTypeId(id);
        if (leaves.size() != 0) {
            throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "当前请假类型正在被使用");
        }
        if (ObjectUtil.isNull(lt)) {
            throw new BusinessException(ResultCodeEnum.PARAM_VALIDATE_FAILED, "当前请假类型id不存在");
        }
        leaveTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public LeaveType selectById(Integer id) {
        return leaveTypeMapper.selectByPrimaryKey(id);
    }
}




