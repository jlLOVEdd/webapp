package com.wdd.test.comm.util;

import test.Teather;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 11:36 2018/8/1
 */
public class LambdaTest {
    public static void main(String[] args) {
        Teather teather = new Teather();
        teather.setName("111");
        teather.setAddress("beijing");
        Teather teather1 = new Teather();
        teather1.setName("222");
        teather1.setAge(22);
        teather1.setAddress("beijing");
        List<Teather> list = new ArrayList<>();
        list.add(teather1);
        list.add(teather);
        list.forEach(vo->{
            vo.setAge(10);
        });
        list.forEach(vo-> System.out.println(vo.getName()+"---------"+vo.getAddress()+"--------"+vo.getAge()));
    }
}
