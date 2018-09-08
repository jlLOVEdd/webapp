package test;

import com.wdd.test.comm.util.ListenerThread;
import com.wdd.test.comm.util.Test;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 18:27 2018/6/19
 */
public class TestMemory {

     class Human {

    }

     class Man extends Human {

    }

     class Woman extends Human {

    }

    public    void sayHello(Human human){
        System.out.println("Huamn");
    }

    public   void sayHello(Woman woman){
        System.out.printf("woman");
    }
    public   void sayHello(Man man){
        System.out.printf("Man");
    }

    public static void main(String[] args) {
        ListenerThread listenerThread=  new ListenerThread();
        listenerThread.start();
    }

}
