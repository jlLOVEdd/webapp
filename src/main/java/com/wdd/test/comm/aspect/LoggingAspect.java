package com.wdd.test.comm.aspect;

import com.wdd.test.comm.annotation.LogAnnotation;
import com.wdd.test.comm.log.ErrorLog;
import com.wdd.test.comm.log.InnerLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

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
        InnerLog.info(joinPoint.getSignature().getDeclaringType() + "---" + joinPoint.getSignature().getName());
    }

    @After(value = "pointCut()")
    public void after(JoinPoint joinpoint) {
        try {
            MethodSignature ms = (MethodSignature) joinpoint.getSignature();
            Method method = ms.getMethod();
            //String methodName = method.getName();
            if (method.isAnnotationPresent(LogAnnotation.class)) {
                LogAnnotation audits = method.getAnnotation(LogAnnotation.class);

            }
        } catch (Exception e) {
            ErrorLog.error("审计日志记录异常:");
        }
    }
}
