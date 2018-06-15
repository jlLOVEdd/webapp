package com.wdd.test.comm.LisenerTest;

/**
 * @Author: weidongdong
 * @Description:事件触发者
 * @Date: Created in 15:38 2018/6/14
 */
public interface FireEvent {
    void addLisener(Lisener lisener);

    void removeLisenner(Lisener lisener);

    void notifyLiseners(String s);

}
