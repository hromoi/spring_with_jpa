package com.spring.application;

import com.spring.application.entity.User;
import com.spring.application.interfaces.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.spring.application");
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        User alex = User.builder().name("alex").email("Zsavinoww@gmail.com").build();
        userService.deleteAll();
        userService.save(alex);
    }
}
