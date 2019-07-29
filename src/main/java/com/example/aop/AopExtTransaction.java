package com.example.aop;

import com.example.annotation.ExtTransaction;
import com.example.transaction.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.lang.reflect.Method;

/**
 * 自定义事务注解具体实现
 */
@Aspect
@Component
public class AopExtTransaction {

    @Autowired
    private TransactionUtils transactionUtils;

    @AfterThrowing("execution(* com.example.service.UserService.save(..))")
    public void afterThrowing() {
        System.out.println("程序发生异常");
        transactionUtils.rollback();
        //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

    // // 环绕通知 在方法之前和之后处理事情
    //@Around("execution(* com.example.service.*.*.*(..))") //下面只对save方法运用手动事务注解
    @Around("execution(* com.example.service.UserService.save(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //1.获取代理对象的方法
        //2.获取该方法上是否加上注解
        //3.如果存在注解，开启事务
        //4.调用目标代理对象方法
        //5.判断该方法上是否加上注解
        // 6.如果存在注解，提交事务

        // 调用方法之前执行
        TransactionStatus transactionStatus = begin(proceedingJoinPoint);
        proceedingJoinPoint.proceed();// 代理调用方法 注意点： 如果调用方法抛出异常不会执行后面代码
        // 调用方法之后执行
        commit(transactionStatus);
    }

    /**
     * 代理方法上有事务注解，则开启事务注解
     *
     * @param pjp
     * @return
     * @throws NoSuchMethodException
     */
    public TransactionStatus begin(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        //获取代理对象方法上注解，有则返回注解
        ExtTransaction declaredAnnotation = getExtTransaction(pjp);
        if (declaredAnnotation == null) {
            return null;
        }
        //如果有自定义事务注解，则开启事务注解
        System.out.println("开启事务");
        TransactionStatus transactionStatus = transactionUtils.begin();
        return transactionStatus;
    }

    /**
     * 获取代理对象方法上注解，有则返回注解
     *
     * @param pjp
     * @return
     * @throws NoSuchMethodException
     */
    public ExtTransaction getExtTransaction(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        // 获取方法名称
        String methodName = pjp.getSignature().getName();
        // 获取目标对象
        Class<?> aClass = pjp.getTarget().getClass();
        // 获取目标对象类型,方法参数
        Class<?>[] par = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        // 获取目标对象方法
        Method objMethod = aClass.getMethod(methodName, par);
        // // 判断是否有自定义事务注解
        ExtTransaction annotation = objMethod.getDeclaredAnnotation(ExtTransaction.class);
        if (annotation == null) {
            System.out.println("您的方法上,没有加入注解!");
            return null;
        }
        return annotation;
    }

    /**
     * 提交事务
     *
     * @param transactionStatus
     */
    public void commit(TransactionStatus transactionStatus) {
        if (transactionStatus != null) {
            System.out.println("提交事务");
            transactionUtils.commit(transactionStatus);
        }
    }

    /**
     * 回滚事务
     *
     * @param pjp
     * @throws NoSuchMethodException
     */
    public void isRollback(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        // // 判断是否有自定义事务注解
        ExtTransaction declaredAnnotation = getExtTransaction(pjp);
        if (declaredAnnotation != null) {
            System.out.println("已经开始回滚事务");
            // 获取当前事务 直接回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

    }
}
