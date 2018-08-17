package com.wdd.test.comm.threadPool;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 9:44 2018/8/17
 */
public class RunnaleDemo implements Runnable {

    private  AtomicInteger i = new AtomicInteger(0);

    public RunnaleDemo() {
    }


    @Override
    public void run() {int j;
        for ( j = 0; j < 10000; j++) {
            i.getAndIncrement();
            System.out.println(Thread.currentThread().getName()+"-------------------"+i);

        }
    }
}
