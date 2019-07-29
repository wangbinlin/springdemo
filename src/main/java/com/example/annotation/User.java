package com.example.annotation;

import java.lang.reflect.Method;

public class User {

    @AddAnnotation(userId =18,userName = "张三",arrays = {"1"})
    public  void add(){
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //怎么获取到方法上注解信息 反射机制
        Class<?> forName = Class.forName("com.example.annotation.User");
        //获取当前类所有方法（不包含集成）
        Method[] declaredMethods = forName.getDeclaredMethods();
        for(Method method :declaredMethods){

            AddAnnotation addAnnotation = method.getDeclaredAnnotation(AddAnnotation.class);
            if(addAnnotation==null){
                System.out.println(method.getName()+",此方法上面没有注解");
                continue;
            }
            System.out.println(method.getName()+",此方法上面注解");
            System.out.println("userId="+addAnnotation.userId());
            System.out.println("userName="+addAnnotation.userName());
            System.out.println("arrays="+addAnnotation.arrays());
        }
    }

}
