package com.wdd.test.comm.util;

import com.wdd.test.comm.log.InnerLog;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 9:06 2018/8/22
 */
@Component
public class InitBean /*implements InitializingBean*/ {

    /*@Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("..................staring......................");
    }*/
    @PostConstruct
    public void startWithSpringMethod() {
        System.out.println("--------------------staring @PostConstruct sout-----------");
        InnerLog.info("--------------------staring @PostConstruct-----------");
    }

    @PreDestroy
    public void endWithSpringMethod() {
        System.out.println("--------------------stopping @PreDestroy sout-----------");
        InnerLog.info("--------------------stopping @PreDestroy-----------");
    }
}
