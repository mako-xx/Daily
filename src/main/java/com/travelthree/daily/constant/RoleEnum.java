package com.travelthree.daily.constant;

/**
 * @author 陈宣辰
 * @date 2022-11-23 16:16:29
 * @description 角色枚举
 */
public enum RoleEnum {
    /** 员工 */
    STAFF,
    /** 管理员 */
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
