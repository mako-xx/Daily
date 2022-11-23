package com.travelthree.daily.dto;

import com.travelthree.daily.constant.RoleEnum;
import lombok.Data;

/**
 * @author 陈宣辰
 * @date 2022-11-23 16:19:00
 * @description 员工数据对象
 */
@Data
public class EmployeeDTO {
    /**
     * 员工id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 角色
     */
    private RoleEnum role;

    /**
     * 联系方式
     */
    private String telephone;

    /**
     * 所属部门id
     */
    private String departmentId;
}
