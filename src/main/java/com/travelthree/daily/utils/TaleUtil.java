package com.travelthree.daily.utils;

import com.travelthree.daily.constant.WebConstant;
import com.travelthree.daily.dto.EmployeeDTO;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 陈宣辰
 * @date 2022-11-23 18:00:24
 * @description
 */
public class TaleUtil {

    /**
     * 获取当前登录用户
     * */
    public static EmployeeDTO getCurrentLoginUser(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        return (EmployeeDTO) request.getSession().getAttribute(WebConstant.LOGIN_SESSION_KEY);
    }

    /**
     * 从Cookie中获取Uid
     * */
    public static String getUidFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null || request.getCookies().length == 0) {
            return null;
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(WebConstant.REMEMBER_COOKIE_KEY)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 清除用户登录认证
     * */
    public static void logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(WebConstant.LOGIN_SESSION_KEY);
        SecurityContextHolder.getContext().setAuthentication(null);
        Cookie cookie = new Cookie(WebConstant.REMEMBER_COOKIE_KEY, "");
        cookie.setMaxAge(0);
        cookie.setPath("/api");
        response.addCookie(cookie);
    }
}
