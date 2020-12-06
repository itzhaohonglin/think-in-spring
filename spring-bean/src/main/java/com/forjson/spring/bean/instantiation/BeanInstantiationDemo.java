package com.forjson.spring.bean.instantiation;

import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * {@link com.forjson.spring.ioc.container.overview.domain.User} Bean 实例化方式
 */
public class BeanInstantiationDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");

        //1. 构造器方式实例化
        User contructorUser = beanFactory.getBean("user-by-constructor", User.class);
        System.out.println("通过构造器方式实例化User:" + contructorUser);

        //2.通过静态工厂实例化
        User staticMethodUser = beanFactory.getBean("user-by-static-method", User.class);
        System.out.println("通过静态大厂方法实例化User:" + staticMethodUser);

        //3通过工厂方法实例化
        User factoryMethodUser = beanFactory.getBean("user-by-factory-method", User.class);
        System.out.println("通过Bean工厂方法实例化User:" + factoryMethodUser);

        //4. 通过FactoryBean 实例化
        User factoryBeanUser = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println("通过FactoryBean 实例化User:" + factoryBeanUser);


    }
}
