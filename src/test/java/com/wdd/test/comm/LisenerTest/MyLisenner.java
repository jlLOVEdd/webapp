package com.wdd.test.comm.LisenerTest;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 15:40 2018/6/14
 */
public class MyLisenner implements Lisener {
    private String name;
    public MyLisenner(String name) {
        this.name = name;
    }

    @Override
    public void eventMessage(String s) {
        System.out.println("my name is "+name);
    }
}
