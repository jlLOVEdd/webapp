package com.wdd.test.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class Aspject {
    public Aspject() {
        // TODO Auto-generated constructor stub
    }

    @Pointcut("execution(* *.add*(..))")
    public void addmethod() {
        System.out.println("I am pointcut method");
    }

    @Before(value = "addmethod()")
    public void setLOg(JoinPoint joinPoint) {
        System.out.println("currentTime:" + System.currentTimeMillis() + "----before");
        System.out.println("MethodName"+"------------"+joinPoint.getSignature().getName());

    }

    @After("addmethod()")
    public void setAfterLOg() {
        System.out.println("currentTime:" + System.currentTimeMillis() + "----After");
    }

}
