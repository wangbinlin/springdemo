```java

TransactionStatus transactionStatus = begin(proceedingJoinPoint);// 调用方法之前执行
proceedingJoinPoint.proceed();// 代理调用方法 注意点： 如果调用方法抛出异常不会执行后面代码
commit(transactionStatus);// 调用方法之后执行

```
