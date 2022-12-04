package com.travelthree.daily.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 响应过滤器
 * 同时统计每次处理请求消耗的时间
 * */
@Slf4j
@Component
@Order(-1)
public class LogFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("请求方法 {} URI {} Query {}", request.getMethod(),request.getRequestURI(),request.getQueryString());
        long startTime = System.currentTimeMillis();
        filterChain.doFilter(request, response);
        log.info("请求处理完成，耗时: {}", System.currentTimeMillis() - startTime);
    }
}
