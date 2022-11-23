package com.travelthree.daily.component;

import cn.hutool.core.util.StrUtil;
import com.travelthree.daily.constant.ResultCodeEnum;
import com.travelthree.daily.constant.WebConstant;
import com.travelthree.daily.dto.EmployeeDTO;
import com.travelthree.daily.exception.BusinessException;
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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 从会话中获取用户信息
        EmployeeDTO user = (EmployeeDTO) request.getSession().getAttribute(WebConstant.LOGIN_SESSION_KEY);
        // 如果携带了会话信息
        if (user != null) {
            try {
                // 还没有进行认证，就先进行认证
                if (SecurityContextHolder.getContext().getAuthentication() == null && StrUtil.isNotBlank(user.getUsername())) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
                    // TODO: 这段逻辑有点怪，需要看下session处理
                    // 会话中的用户是伪造的，抛出异常
                    if (!userDetails.getPassword().equals(user.getPassword())) {
                        throw new BusinessException(ResultCodeEnum.LOGIN_FAILED);
                    }
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (Exception e) {
                log.warn("认证失败");
                throw new BadCredentialsException(e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}
