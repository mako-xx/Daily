package com.travelthree.daily.component;

import com.travelthree.daily.dto.AdminUserDetails;
import com.travelthree.daily.dto.EmployeeDTO;
import com.travelthree.daily.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author 陈宣辰
 * @date 2022-11-23 16:01:40
 * @description
 */
@Component
public class AuthUserDetailService implements UserDetailsService {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeDTO employee = employeeService.getEmployeeByUsername(username);
        return new AdminUserDetails(employee);
    }
}
