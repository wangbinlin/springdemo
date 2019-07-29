package com.example;

import com.example.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientExtTransaction
{
    public static void main(String[] args) {
        //手动事务注解，类似于@Transaction
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        userService.save();
        System.out.println("Hello World!");
    }
}
