package com.travelthree.daily.cron;

import com.travelthree.daily.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AttendanceSchedule {

    @Autowired
    private AttendanceService attendanceService;

    /**
     * 每天早上8点定时开启考勤
     * */
    @Scheduled(cron = "0 0 8 * * * ")
    public void openAttendance() {
        log.info("开启考勤");
        try {
            // TODO：添加重试机制
            attendanceService.addAttend();
        } catch (Exception e) {
            log.info("添加考勤失败，请联系开发人员");
            throw e;
        }

    }
}
