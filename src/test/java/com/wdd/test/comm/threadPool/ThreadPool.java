package com.wdd.test.comm.threadPool;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 15:46 2018/3/21
 */
public class ThreadPool {
    volatile static int i = 0;

    public static void main(String[] args) {

//        ExecutorService pool = Executors.newCachedThreadPool();
//        Thread t1= new Thread();
//        pool.execute(t1);
//
        RunnaleDemo ss = new RunnaleDemo();
        for (int i = 0; i < 30; i++) {
            Thread thread = new ThreadDemo();
            thread.setName("thread" + i);
            thread.start();
            /*Thread thread = new Thread(ss);
            thread.setName("Thread"+i);
            thread.start();*/
        }

    }

}
