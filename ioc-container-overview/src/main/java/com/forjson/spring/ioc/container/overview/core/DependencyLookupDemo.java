package com.forjson.spring.ioc.container.overview.core;

import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyLookupDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-lookup-context.xml");
        //实时查找
        lookupByRealTime(beanFactory);
        //延时查找
        lookupByLazy(beanFactory);
        //根据类型查找
        lookupByType(beanFactory);
        //根据注解查找
        lookupByAnnotation(beanFactory);
    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {

    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("根据类型查找:" + user);
    }

    private static void lookupByLazy(BeanFactory beanFactory) {
        ObjectFactory<User> userObjectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = userObjectFactory.getObject();
        System.out.println("延时查找:" + user);
    }

    private static void lookupByRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找:" + user);
    }
}
