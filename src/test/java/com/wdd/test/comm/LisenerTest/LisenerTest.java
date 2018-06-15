package com.wdd.test.comm.LisenerTest;

import java.awt.*;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 15:47 2018/6/14
 */
public class LisenerTest {
    public static void main(String[] args) {
        MyLisenner lisener = new MyLisenner("weidongdong");
        Lisener lisener1 = new MyLisenner("张三");
        Lisener lisener2 = new MyLisenner("李四");
        FireEvent event = new MyEvent();
        event.addLisener(lisener);
        event.addLisener(lisener1);
        event.addLisener(lisener2);
        event.notifyLiseners("");

    }
}
