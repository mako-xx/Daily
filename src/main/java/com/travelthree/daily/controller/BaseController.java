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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    public String login(@RequestParam @Valid @NotBlank String username,
                        @RequestParam @Valid @NotBlank String password,
                        @RequestParam(required = false) boolean remember,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        TaleUtil.logout(request, response);
        try {
            EmployeeDTO employeeDTO = employeeService.login(username, password);
            request.getSession().setAttribute(WebConstant.LOGIN_SESSION_KEY, employeeDTO);
            request.getSession().setAttribute(WebConstant.LOGIN_USER_ROLE, employeeDTO.getRole());
            if (remember) {
                response.addCookie(new Cookie(WebConstant.REMEMBER_COOKIE_KEY, employeeDTO.getId()));
            }
        } catch (Exception e) {
            request.setAttribute("msg", "用户名或密码错误");
            return "index";
        }
        // welcome界面url
        return "redirect:/welcome";
    }

    @PostMapping("/api/logout")
    @ResponseBody
    public CommonResult logout(HttpServletRequest request, HttpServletResponse response) {

        TaleUtil.logout(request, response);
        return CommonResult.success();
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}
