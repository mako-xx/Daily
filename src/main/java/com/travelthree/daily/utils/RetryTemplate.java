package com.travelthree.daily.utils;

import com.travelthree.daily.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

/**
 * 重试代码模板
 * */
@Slf4j
public abstract class RetryTemplate {

    /** 默认重试时长 */
    private static final int DEFAULT_RETRY_COUNT = 1;

    /** 重试时长 */
    private int retryCount = DEFAULT_RETRY_COUNT;

    /** 重试间隔 */
    private int interval = 0;

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    /** 重试执行的任务
     *
     * @throws Exception 根据是否抛出异常判断重试是否成功
     * */
    protected abstract Object task() throws Exception;

    public Object execute() throws InterruptedException {
        for (int i = 0; i < retryCount; i++) {
            try {
                return task();
            } catch (BusinessException e) {
                log.info("重试出现业务异常，退出重试，错误原因：{}", e.getMessage());
                return null;
            } catch (Exception e) {
                Thread.sleep(interval);
            }
        }
        // 重试失败，记录日志
        log.info("任务重试失败");
        return null;
    }

    public Object submit(ExecutorService executorService) {
        if (executorService == null) {
            throw new IllegalArgumentException("线程池不可为空");
        }
        return executorService.submit(this::task);
    }
}
