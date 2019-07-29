package com.example.aop;

import com.example.transaction.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 切面类-基于手动事务封装
 *
 * @author wangbinlin
 * @version V1.0
 * @date 2019/7/25
 */
@Component
@Aspect
public class AopTransaction {
@Autowired
private TransactionUtils transactionUtils;

    @AfterThrowing("execution(* com.example.service.UserService.add(..))")
    public  void afterThrowing(){
        System.out.println("异常-回滚事务");
        // 获取当前事务 直接回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

    @Around("execution(* com.example.service.UserService.add(..))")
    public  void  around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 调用方法之前执行
        System.out.println("开启事务");
        TransactionStatus transactionStatus = transactionUtils.begin();
        proceedingJoinPoint.proceed();// 代理调用方法 注意点： 如果调用方法抛出溢出不会执行后面代码
        // 调用方法之后执行
        System.out.println("提交事务");
        transactionUtils.commit(transactionStatus);

    }
}
