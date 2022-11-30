package com.travelthree.daily.vo;

import com.travelthree.daily.constant.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeVo implements Serializable {
    private String id;

    private String username;

    private String password;

    private String name;

    private RoleEnum role;

    private String telephone;

    private String department;

}
