package com.forjson.spring.ioc.container.overview.core;

import com.forjson.spring.ioc.container.overview.annotation.Super;
import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class DependencyLookupDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-lookup-context.xml");
        //实时查找
        lookupByRealTime(beanFactory);
        //延时查找
        lookupByLazy(beanFactory);
        //根据类型查找
        lookupByType(beanFactory);
        lookupCollectionByType(beanFactory);
        //根据注解查找
        lookupByAnnotation(beanFactory);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("集合类型查找:"+users);
        }
    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Object> beansWithAnnotation = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("根据注解方式查找:"+beansWithAnnotation);
        }
    }

    /**
     * 只能获取一个相同的类型,否则会报错
     * 解决方案: 在xml中配置主要类型 . "primary = "true""
     * @param beanFactory
     */
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
