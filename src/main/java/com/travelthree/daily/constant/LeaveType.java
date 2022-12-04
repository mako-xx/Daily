package com.travelthree.daily.constant;

/**
 * @author 陈宣辰
 * @date 2022-11-23 17:42:44
 * @description 请假类型枚举
 */
public enum LeaveType {

    /**
     * 病假(0)，事假(1)，婚假(2)，丧假(3)，产假(4)，其他(5)
     */

    /** 病假(0) */
    SICK,
    /** 事假(1) */
    AFFAIRS,
    /** 婚假(2) */
    MARRIAGE,
    /** 丧假(3) */
    FUNERAL,
    /** 产假(4) */
    MATERNITY,
    /** 其他(5) */
    ELSE;

    public static LeaveType getRoleFromOrdinal(int ordinal) {
        for (LeaveType value : LeaveType.values()) {
            if (value.ordinal() == ordinal) {
                return value;
            }
        }
        return null;
    }
}
