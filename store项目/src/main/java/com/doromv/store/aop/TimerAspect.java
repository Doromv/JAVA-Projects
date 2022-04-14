package com.doromv.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Doromv
 * @create 2022-04-14-9:13
 */
@Component
@Aspect
public class TimerAspect {
    @Around("execution(* com.doromv.store.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
        return result;
    }
}
