package com.wdd.test.comm.util;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 17:34 2018/7/24
 */
public class KafkaTest {
    public static void main(String[] args) {
        try {
            KafkaProducerUtils.getInstance().sendMsg("ssss1");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
