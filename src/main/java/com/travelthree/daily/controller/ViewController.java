package com.travelthree.daily.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 陈宣辰
 * @date 2022-11-27 17:05:05
 * @description
 */
@Controller
@RequestMapping("/view")
public class ViewController {
    @GetMapping("/test1")
    public String test() {
        return "index";
    }

    // 员工打卡记录
    @GetMapping("/test2")
    public String test2() {
        return "employee/checkin";
    }

    // 员工请假记录
    // 请假类型
    @GetMapping("/test3")
    public String test3() {
        return "employee/leave";
    }

    // 员工列表
    // 部门列表
    @GetMapping("/test4")
    public String test4() {
        return "admin/employees";
    }

    // 部门列表（不分页）
    // 部门列表（分页）
    @GetMapping("/test5")
    public String test5() {
        return "admin/departments";
    }

    // 请假类型(分页)
    @GetMapping("/test6")
    public String test6() {
        return "admin/leavetypes";
    }

    @GetMapping("/test7")
    public String test7() {
        return "admin/checkins";
    }

    // 请假申请（分页）
    @GetMapping("/test8")
    public String test8() { return "admin/checkleaves";}
}
