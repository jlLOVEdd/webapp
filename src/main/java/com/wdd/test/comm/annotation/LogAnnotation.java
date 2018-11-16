package com.wdd.test.comm.annotation;

import java.lang.annotation.*;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 11:25 2018/11/16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String value() default "";
    String tableName();
}
