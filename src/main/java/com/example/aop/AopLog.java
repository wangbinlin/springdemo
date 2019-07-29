package com.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * AopLog切面类,aop 编程里面有几个通知： 前置通知 后置通知 运行通知 异常通知 环绕通知
 *
 * @author wangbinlin
 * @version V1.0
 * @date 2019/7/25
 */
@Component
@Aspect
public class AopLog {
    //前置通知 后置通知 运行通知 异常通知 环绕通知

    @Before("execution(* com.example.service.UserService.add(..))")
    public  void  before(){
        System.out.println("前置通知，在方法之前执行...");
    }

    @After("execution(* com.example.service.UserService.add(..))")
    public  void after(){
        System.out.println("前置通知 在方法之后执行...");
    }

    @AfterReturning("execution(* com.example.service.UserService.add(..))")
    public  void afterReturning(){
        System.out.println("运行通知");
    }

    @AfterThrowing("execution(* com.example.service.UserService.add(..))")
    public  void afterThrowing(){
        System.out.println("异常通知");
    }

    @Around("execution(* com.example.service.UserService.add(..))")
    public void  around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知 调用方法之前执行");
        proceedingJoinPoint.proceed();
        System.out.println("环绕通知 调用方法之后执行");

    }


}
