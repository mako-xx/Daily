package com.travelthree.daily.controller.admin;

import com.github.pagehelper.PageInfo;
import com.travelthree.daily.domain.Employee;
import com.travelthree.daily.dto.RegisterParam;
import com.travelthree.daily.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    @ResponseBody
    public String register(@Valid @RequestBody RegisterParam registerParam) {
        return employeeService.register(registerParam);
    }

    @RequestMapping("/employee")
    @ResponseBody
    public PageInfo queryEmployee(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "5") Integer pageSize) {
        //为了程序的严谨性，判断非空：
        if(pageNum == null || pageNum <= 0){
            pageNum = 1;   //设置默认当前页为1
        }
        if(pageSize == null){ //若页面大小为空
            pageSize = 5;    //设置默认每页显示的数据数
        }
        List<Employee> employees = employeeService.queryEmployeeByPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(employees);
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        return pageInfo;
    }
}
