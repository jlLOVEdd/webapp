package com.wdd.test.comm.util;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 11:29 2018/7/25
 */
public class KafkaClumerTest {
    public static void main(String[] args) {
        try {
            ListenerThread listener = new ListenerThread();

            listener.setName("ssssss");
            listener.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
