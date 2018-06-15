package com.wdd.test.comm.LisenerTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 15:41 2018/6/14
 */
public class MyEvent implements FireEvent {

    private List<Lisener> list = new ArrayList<Lisener>();

    @Override
    public void addLisener(Lisener lisener) {
        list.add(lisener);
    }

    @Override
    public void removeLisenner(Lisener lisener) {
        list.remove(lisener);
    }

    @Override
    public void notifyLiseners(String s) {
        list.forEach(n-> n.eventMessage(s));
    }
}
