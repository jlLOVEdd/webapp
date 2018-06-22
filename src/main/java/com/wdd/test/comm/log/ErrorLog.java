package com.wdd.test.comm.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: weidongdong
 * @Description:系统操作错误级别 ----logback
 * @Date: Created in 11:05 2018/6/22
 */
public class ErrorLog {
    private  final  static Logger logger = LoggerFactory.getLogger("WebApp_error");

    public static void error(String msg){
        logger.error(msg);
    }
}
