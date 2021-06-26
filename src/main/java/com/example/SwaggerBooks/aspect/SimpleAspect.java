package com.example.SwaggerBooks.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy
@Aspect
@Component
@Slf4j
public class SimpleAspect {

    @Pointcut("execution(* com.example.SwaggerBooks..*(..))")
    public void anyMethod(){ }

    @Before("anyMethod()")
    public void beforeAnyMethod(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getDeclaringTypeName());
        log.info("... before any method ... "+joinPoint.getSignature().getName());
    }
}
