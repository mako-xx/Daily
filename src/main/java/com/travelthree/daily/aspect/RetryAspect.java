package com.travelthree.daily.aspect;

import com.travelthree.daily.exception.BusinessException;
import com.travelthree.daily.utils.RetryTemplate;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Aspect
@Slf4j
@Component
public class RetryAspect {

    ExecutorService executorService = new ThreadPoolExecutor(3, 5,
            1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>());

    @Around(value = "@annotation(retry)")
    public Object execute(ProceedingJoinPoint joinPoint, Retry retry) throws InterruptedException {
        RetryTemplate retryTemplate = new RetryTemplate() {
            @Override
            protected Object task() throws Exception {
                try {
                    return joinPoint.proceed();
                } catch (BusinessException e) {
                    throw e;
                } catch (Throwable e) {
                    throw new Exception(e);
                }
            }
        };
        retryTemplate.setRetryCount(retry.count());
        retryTemplate.setInterval(retry.interval());
        if (retry.async()) {
            return retryTemplate.submit(executorService);
        } else {
            return retryTemplate.execute();
        }
    }
}
