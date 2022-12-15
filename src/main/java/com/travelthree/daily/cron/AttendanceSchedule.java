package com.travelthree.daily.cron;

import com.travelthree.daily.aspect.Retry;
import com.travelthree.daily.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@Slf4j
public class AttendanceSchedule {

    @Autowired
    private AttendanceService attendanceService;

    private AtomicBoolean enabled = new AtomicBoolean(false);

    /**
     * 每天早上8点定时开启考勤
     * */
    @Scheduled(cron = "0/10 * * * * * ")
    @Retry(async = true)
    public void openAttendance() {
        if (enabled.get()) {
            log.info("开启考勤");
            attendanceService.addAttend();
        }
    }

    public void enable() {
        enabled.set(true);
    }

    public void disable() {
        enabled.set(false);
    }
}
