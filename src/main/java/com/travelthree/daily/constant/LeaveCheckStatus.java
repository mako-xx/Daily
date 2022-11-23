package com.travelthree.daily.constant;

/**
 * @author 陈宣辰
 * @date 2022-11-23 17:41:02
 * @description 请假审核枚举
 */
public enum LeaveCheckStatus {
    /** 等待审核 */
    WAITING,
    /** 拒绝 */
    REFUSE,
    /** 通过 */
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
