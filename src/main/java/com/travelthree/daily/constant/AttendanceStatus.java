package com.travelthree.daily.constant;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 陈宣辰
 * @date 2022-11-23 17:38:56
 * @description 考勤状态
 */
public enum AttendanceStatus {
    /** 出勤 */
    @JsonProperty("WORK")
    WORK,
    /** 缺勤 */
    @JsonProperty("ABSENT")
    ABSENT,
    /** 请假 */
    @JsonProperty("LEAVE")
    LEAVE;

    public static AttendanceStatus getStatusFromOrdinal(int ordinal) {
        for (AttendanceStatus value : AttendanceStatus.values()) {
            if (value.ordinal() == ordinal) {
                return value;
            }
        }
        return null;
    }
}
