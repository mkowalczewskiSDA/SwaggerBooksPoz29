package com.example.SwaggerBooks.aspect;

import com.example.SwaggerBooks.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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

    @After("anyMethod()")
    public void afterAnyMethod(JoinPoint joinPoint) {
        log.info("... after any method ... "+joinPoint.getSignature().getName());
    }

    @Around("execution(* com.example.SwaggerBooks.controller..*(..))")
    public void aroundMethodInController(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("start");
        var time = System.currentTimeMillis();
        joinPoint.proceed();
        log.info("method took "+(System.currentTimeMillis()-time)+"ms of execution");
        System.out.println("stop");
    }

    @Before("@annotation(annotation) && args(book,..)")
    public void beforeAnnotated(ExampleAnnotation annotation, Book book) {
        System.out.println("Metoda z adnotacją");
        log.info(book.getTitle());
    }
}
