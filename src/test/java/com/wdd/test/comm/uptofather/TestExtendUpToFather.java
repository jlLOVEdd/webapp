package com.wdd.test.comm.uptofather;

public class TestExtendUpToFather {
    public static void main(String[] args) {
        Father father = new Son();
        Father f = new Father();
        Son son = new Son();
        Son s =(Son) f;
        father.eat();
        f.eat();
        s.eat();
    }
}
