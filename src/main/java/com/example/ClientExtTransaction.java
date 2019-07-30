package com.example;

import com.example.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientExtTransaction
{
    public static void main(String[] args) {
        //手写-自定义事务注解，类似于@Transactional
        //1.自定义注解：ExtTransaction
        //2.自定义AOP事务切面,运用到save()方法上：AopExtTransaction
        //2.1获取代理对象方法上有自定义注解，有则返回注解
        //2.2并在在代理对象方法上开启事务

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        userService.save();
        System.out.println("Hello World!");

        //使用事务注解@Transactional
        /*
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        userService.save2();
        System.out.println("Hello World!");
        */
    }
}
