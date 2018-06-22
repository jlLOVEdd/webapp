package com.wdd.test.comm.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: weidongdong
 * @Description: 接口调用日志 -----logback
 * @Date: Created in 11:08 2018/6/22
 */
public class InnerLog {
    private final static Logger log = LoggerFactory.getLogger("InnerInfo");

    public static void error(String message, Throwable e) {
        log.error(message, e);
    }

    public static void error(String message) {
        log.error(message);
    }

    public static void warn(String message) {
        log.warn(message);
    }

    public static void info(String message) {
        log.info(message);
    }

    public static void info(String format, Object... arguments) {
        log.info(format, arguments);
    }

    public static void debug(String message) {
        log.debug(message);
    }

    public static void debug(String format, Object... arguments) {
        log.debug(format, arguments);
    }

}
