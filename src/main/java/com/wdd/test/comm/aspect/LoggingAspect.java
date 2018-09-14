package com.wdd.test.comm.aspect;

import com.wdd.test.comm.log.InnerLog;
import com.wdd.test.comm.util.JedisUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 9:41 2018/9/4
 */
/*@Component
@Aspect*/
public class LoggingAspect {

    @Autowired
    private HttpServletRequest request;

    @Pointcut("execution(* com.wdd.test.service.*.*(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        //System.out.println(this.request.getMethod());
        System.out.println("GET METHOD START。。。。。。。。。");
        InnerLog.info(joinPoint.getSignature().getDeclaringType()+"---"+joinPoint.getSignature().getName());
    }

    @After("pointCut()")
    public void after() {

        System.out.println("logging after starting..............");

    }
}
