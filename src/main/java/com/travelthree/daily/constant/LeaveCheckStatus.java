package com.travelthree.daily.constant;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 陈宣辰
 * @date 2022-11-23 17:41:02
 * @description 请假审核枚举
 */
public enum LeaveCheckStatus {
    /** 等待审核 */
    @JsonProperty("WAITING")
    WAITING,
    /** 拒绝 */
    @JsonProperty("REFUSE")
    REFUSE,
    /** 通过 */
    @JsonProperty("APPROVE")
    APPROVE;

    public static LeaveCheckStatus getRoleFromOrdinal(int ordinal) {
        for (LeaveCheckStatus value : LeaveCheckStatus.values()) {
            if (value.ordinal() == ordinal) {
                return value;
            }
        }
        return null;
    }
}
