package com.forjson.spring.ioc.container.overview.core;

import com.forjson.spring.ioc.container.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyInjectDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-inject-context.xml");
        UserRepository userRepository = beanFactory.getBean(UserRepository.class);
        System.out.println("userRepository.userList:"+userRepository.getUserList());
    }

}
