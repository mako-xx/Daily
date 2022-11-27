package com.travelthree.daily.dto;

import com.travelthree.daily.domain.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author 陈宣辰
 * @date 2022-11-23 16:14:59
 * @description
 */
public class AdminUserDetails implements UserDetails {

    private EmployeeDTO employee;

    private List<GrantedAuthority> authorityList;

    public AdminUserDetails(EmployeeDTO employee) {
        this.employee = employee;
        authorityList = Collections.singletonList(new SimpleGrantedAuthority(employee.getRole().name()));
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return employee.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
