package com.forjson.spring.ioc.container.overview.dependency.injection;

import com.forjson.spring.ioc.container.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyInjectionDemo {
    public static void main(String[] args) {
        //配置XML配置文件
        //启动XML配置文件
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-inject-context.xml");

        UserRepository userRepository = beanFactory.getBean(UserRepository.class);
        //依赖注入
        System.out.println(userRepository.getBeanFactory());
        System.out.println(beanFactory == userRepository.getBeanFactory());
        //依赖查找 : org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'org.springframework.beans.factory.BeanFactory' available
        System.out.println(beanFactory.getBean(BeanFactory.class));
    }

}
