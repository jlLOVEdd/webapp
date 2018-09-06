package com.wdd.test.comm.util;

import com.wdd.test.comm.log.InnerLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 9:06 2018/8/22
 */
@Component
public class InitBean implements InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(InitBean.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        InnerLog.error("--------------------staring @PostConstruct-----------");
        System.out.println("--------------------staring @PostConstruct sout-----------");
        InnerLog.error("--------------------staring @PostConstruct-----------");
        System.out.println("ssss");
        System.out.println("..................staring......................");
    }
   /* @PostConstruct
    public void startWithSpringMethod() {
        InnerLog.error("--------------------staring @PostConstruct-----------");
        System.out.println("--------------------staring @PostConstruct sout-----------");
        InnerLog.error("--------------------staring @PostConstruct-----------");
        System.out.println("ssss");
    }

    @PreDestroy
    public void endWithSpringMethod() {
        System.out.println("--------------------stopping @PreDestroy sout-----------");
        InnerLog.error("--------------------stopping @PreDestroy-----------");
    }*/
}
