package com.travelthree.daily.constant;

/**
 * @author 陈宣辰
 * @date 2022-11-23 17:42:44
 * @description 请假类型枚举
 */
public enum LeaveType {
    /** 病假 */
    SICK,
    /** 事假 */
    AFFAIRS,
    /** 婚假 */
    MARRIAGE,
    /** 丧假 */
    FUNERAL,
    /** 产假 */
    MATERNITY,
    /** 其他 */
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
