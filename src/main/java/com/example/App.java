package com.example;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        //对方法进行手动事务begin,commit,rollback。手动事务用在add()方法上，运用到AOP技术,和事务手动编写
        //1.事务工具类：TransactionUtils
        //2.编写事务aop运用到add()方法：AopTransaction
        //3.运行add()方法.
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        userService.add();
        System.out.println("Hello World!");
    }
}
