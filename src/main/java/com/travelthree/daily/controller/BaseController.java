package com.travelthree.daily.controller;

import com.travelthree.daily.constant.WebConstant;
import com.travelthree.daily.dto.EmployeeDTO;
import com.travelthree.daily.dto.LoginParam;
import com.travelthree.daily.service.EmployeeService;
import com.travelthree.daily.utils.TaleUtil;
import com.travelthree.daily.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author 陈宣辰
 * @date 2022-11-23 17:12:38
 * @description
 */
@Controller
@RequestMapping
public class BaseController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping({"", "/index", "/login"})
    public String login() {
        return "index";
    }

    @PostMapping("/api/login")
    @ResponseBody
    public CommonResult login(@RequestBody @Valid LoginParam param, HttpServletRequest request, HttpServletResponse response) {
        TaleUtil.logout(request, response);
        EmployeeDTO employeeDTO = employeeService.login(param.getUsername(), param.getPassword());
        request.getSession().setAttribute(WebConstant.LOGIN_SESSION_KEY, employeeDTO);
        if (param.getRemember()) {
            response.addCookie(new Cookie(WebConstant.REMEMBER_COOKIE_KEY, employeeDTO.getId()));
        }
        return CommonResult.success();
    }

    @PostMapping("/api/logout")
    @ResponseBody
    public CommonResult logout(HttpServletRequest request, HttpServletResponse response) {

        TaleUtil.logout(request, response);
        return CommonResult.success();
    }
}
