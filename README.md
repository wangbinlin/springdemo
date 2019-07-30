# springdemo
## 1.对方法运用手动事务begin,commit,rollback。运用到AOP技术，和手动事务编写
### 1.1编写事务工具类：TransactionUtils
### 1.2编写事务aop运用到add()方法：AopTransaction
### 1.3.客户端类App，运行add()方法
<br/>
## 2.手写-自定义事务注解，类似于@Transactional
### 2.1自定义注解：ExtTransaction
### 2.2.自定义AOP事务切面,环绕通知Around运用到save()方法上：AopExtTransaction
#### 2.2.1获取代理对象方法上是否有自定义注解@ExtTransaction，有则返回注解
#### 2.2.2并在在代理对象方法上开启事务

```java
TransactionStatus transactionStatus = begin(proceedingJoinPoint);// 调用方法之前执行
proceedingJoinPoint.proceed();// 代理调用方法 注意点： 如果调用方法抛出异常不会执行后面代码
commit(transactionStatus);// 调用方法之后执行
```

<br/>
## 3.手写-自定义注解，AddAnnotation


