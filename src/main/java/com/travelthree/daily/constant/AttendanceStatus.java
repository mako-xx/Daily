package com.travelthree.daily.constant;

/**
 * @author 陈宣辰
 * @date 2022-11-23 17:38:56
 * @description 考勤状态
 */
public enum AttendanceStatus {
    /** 出勤 */
    WORK,
    /** 缺勤 */
    ABSENT,
    /** 请假 */
    LEAVE;

    public static AttendanceStatus getRoleFromOrdinal(int ordinal) {
        for (AttendanceStatus value : AttendanceStatus.values()) {
            if (value.ordinal() == ordinal) {
                return value;
            }
        }
        return null;
    }
}
