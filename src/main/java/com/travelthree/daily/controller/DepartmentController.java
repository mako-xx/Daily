package com.travelthree.daily.controller;

import com.travelthree.daily.service.DepartmentService;
import com.travelthree.daily.vo.CommonResult;
import com.travelthree.daily.vo.DepartmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jiayi Zhu
 * @description
 * @createDate 2022/12/5
 */
@Controller
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    @ResponseBody
    public CommonResult getDepartmentList() {

        return CommonResult.success(departmentService.selectAllDepartments().stream()
                .map(department -> new DepartmentVo(
                        department.getId(),
                        department.getName(),
                        departmentService.getSuperiorName(department)
                )));
    }
}
