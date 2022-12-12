package com.travelthree.daily.controller;

import com.travelthree.daily.domain.LeaveType;
import com.travelthree.daily.service.LeaveTypeService;
import com.travelthree.daily.vo.CommonResult;
import com.travelthree.daily.vo.LeaveTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Jiayi Zhu
 * @description
 * @createDate 2022/12/2
 */
@Controller
@RequestMapping("/api/leave-status")
public class LeaveTypeController {

    @Autowired
    private LeaveTypeService leaveTypeService;

    @GetMapping
    @ResponseBody
    public CommonResult getLeaveTypeList() {

        List<LeaveTypeVo> data = leaveTypeService.getAllLeaveTypes()
                .stream().map(leaveType -> new LeaveTypeVo(
                        leaveType.getId().toString(),
                        leaveType.getName()
                )).toList();
        return CommonResult.success(data);
    }

}
