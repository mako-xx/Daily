package com.travelthree.daily.component;

import cn.hutool.json.JSONUtil;
import com.travelthree.daily.vo.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈宣辰
 * @date 2022-11-23 16:55:09
 * @description
 */
@Component
public class DailyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(CommonResult.forbidden("您的帐号没有权限访问该接口")));
        response.getWriter().flush();
    }
}
