package com.travelthree.daily.aspect;

import java.lang.annotation.*;

/** 重试方法注解 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {

    /** 重试次数 */
    int count() default 1;

    /** 重试间隔 */
    int interval() default 0;

    /** 是否异步 */
    boolean async() default false;
}
