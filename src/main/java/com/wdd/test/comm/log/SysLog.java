package com.wdd.test.comm.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 20:25 2018/9/10
 */
@Component
public class SysLog {

    private final static Logger log = LoggerFactory.getLogger("system");

    public static void error(String message, Throwable e) {
        log.error(message, e);
    }

    public static void error(String message) {
        log.error(message);
    }
    public static void error(String format, Object... arguments) {
        log.error(format, arguments);
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
