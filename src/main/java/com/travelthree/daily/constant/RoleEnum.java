package com.travelthree.daily.constant;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 陈宣辰
 * @date 2022-11-23 16:16:29
 * @description 角色枚举
 */
public enum RoleEnum {
    /** 员工 */
    @JsonProperty("STAFF")
    STAFF,
    /** 管理员 */
    @JsonProperty("ADMIN")
    ADMIN;

    public static RoleEnum getRoleFromOrdinal(int ordinal) {
        for (RoleEnum value : RoleEnum.values()) {
            if (value.ordinal() == ordinal) {
                return value;
            }
        }
        return null;
    }
}
