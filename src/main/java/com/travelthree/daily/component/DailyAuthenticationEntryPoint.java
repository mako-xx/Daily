package com.travelthree.daily.component;

import cn.hutool.json.JSONUtil;
import com.travelthree.daily.vo.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈宣辰
 * @date 2022-11-23 16:56:23
 * @description
 */
@Component
public class DailyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        if (authException.getMessage().contains("口令已过期")) {
//            response.getWriter().println(JSONUtil.parse(CommonResult.unauthorized(authException.getMessage())));
            response.sendRedirect("/");
        }
        else {
//            response.getWriter().println(JSONUtil.parse(CommonResult.unauthorized("您当前未登录或通行证已失效")));
            response.sendRedirect("/");
        }
//        response.getWriter().flush();
    }
}
