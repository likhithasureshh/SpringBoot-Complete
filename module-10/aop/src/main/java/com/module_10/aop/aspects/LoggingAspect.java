package com.module_10.aop.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect
@Slf4j
@Component
public class LoggingAspect {

    //@Before("execution(* com.module_10.aop.services.impl.ShipmentServiceImpl.orderPackage(..))")
    @Before("commonServicePointCut()")
    public void beforeOrderPackage(JoinPoint joinPoint)
    {
        log.info("Before in LoggingAspect kind : {}",joinPoint.getKind());
        log.info("Before in LoggingAspect signature : {}",joinPoint.getSignature());
    }

    @Before("within(com.module_10.aop..*)")
    public void beforeServiceCalls()
    {
        log.info("Before service calls..");
    }

    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void beforeTransactionalAnnotation()
    {
        System.out.println("before transactional annotation");
    }

    @Before("@annotation(com.module_10.aop.aspects.MyLogging)")
    public void beforeMyLogging()
    {
        System.out.println("Before annotation MyLogging");
    }

    @Pointcut("execution(* com.module_10.aop.services.impl.ShipmentServiceImpl.*(..))")
    public void commonServicePointCut()
    {

    }
}
