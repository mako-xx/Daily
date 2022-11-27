package com.travelthree.daily.component;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.travelthree.daily.constant.WebConstant;
import com.travelthree.daily.dto.AdminUserDetails;
import com.travelthree.daily.dto.EmployeeDTO;
import com.travelthree.daily.service.EmployeeService;
import com.travelthree.daily.utils.TaleUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈宣辰
 * @date 2022-11-23 15:43:15
 * @description
 */
@Component
@Slf4j
public class DailyAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private EmployeeService employeeService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 从会话中获取用户信息
        EmployeeDTO user = TaleUtil.getCurrentLoginUser(request);
        String uid = TaleUtil.getUidFromCookie(request);
        // 如果携带了会话信息，直接放行
        if (user != null) {
            try {
                // 还没有进行认证，就先进行认证
                if (SecurityContextHolder.getContext().getAuthentication() == null && StrUtil.isNotBlank(user.getUsername())) {
                    AdminUserDetails userDetails = (AdminUserDetails) userDetailsService.loadUserByUsername(user.getUsername());
                    // 会话中的用户不存在于数据库，认证失败
                    if (userDetails.getPassword().equals(user.getPassword())) {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        request.getSession().setAttribute(WebConstant.LOGIN_SESSION_KEY, userDetails.getEmployee());
                    }
                }
            } catch (Exception e) {
                throw new BadCredentialsException(e.getMessage());
            }
        } else if (StrUtil.isNotBlank(uid)) {
            EmployeeDTO employeeDTO = employeeService.getEmployeeById(uid);
            if (ObjectUtil.isNotNull(employeeDTO)) {
                UserDetails userDetails = new AdminUserDetails(employeeDTO);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                request.getSession().setAttribute(WebConstant.LOGIN_SESSION_KEY, employeeDTO);
            }
        }
        filterChain.doFilter(request, response);
    }
}
