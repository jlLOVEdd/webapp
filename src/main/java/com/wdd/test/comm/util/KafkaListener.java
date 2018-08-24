package com.wdd.test.comm.util;

import com.wdd.test.comm.log.InnerLog;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 16:28 2018/5/8
 */
@Component("KafkaListener")
public class KafkaListener implements ApplicationListener<ContextRefreshedEvent> {

    public KafkaListener() {
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            InnerLog.info("sssssssssssssssssssss");
        }

    }
}


