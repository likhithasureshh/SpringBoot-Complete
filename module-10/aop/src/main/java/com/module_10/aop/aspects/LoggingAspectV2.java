package com.module_10.aop.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspectV2 {

    @Before("serviceMethodsPointCut()")
    public void beforeMethodCalls(JoinPoint joinPoint)
    {
        log.info("Before method call for : {}",joinPoint.getSignature());
    }

   // @After("serviceMethodsPointCut()")
    //@AfterReturning(value = "serviceMethodsPointCut()",returning = "returnObj")
    @AfterThrowing("serviceMethodsPointCut()")
    public void afterMethodCall(JoinPoint joinPoint)
    {
        log.info("After Method call for : {}",joinPoint.getSignature());
        //log.info("After call of orderService returning : {}",returnObj);
    }

    @Around("serviceMethodsPointCut()")
    public Object validateOrderId(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        Long orderId = (Long) args[0];
        if(orderId > 0)
        {
           return proceedingJoinPoint.proceed();
        }
        return "Cannot invoke orderService since the orderId is Negative";
    }

    @Around("serviceMethodsPointCut()")
    public Object computeExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        proceedingJoinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        long diff = endTime-startTime;
        return "Total Execution time : "+diff;
    }

    @Pointcut("execution(* com.module_10.aop.services.impl.ShipmentServiceImpl.*(..))")
    public void serviceMethodsPointCut()
    {

    }
}
