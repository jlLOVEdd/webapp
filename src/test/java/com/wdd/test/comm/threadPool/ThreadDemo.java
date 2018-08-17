package com.wdd.test.comm.threadPool;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 9:49 2018/8/17
 */
public class ThreadDemo extends Thread {
    private static int j =0;
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            j++;
            System.out.println(j);
        }
    }
}
