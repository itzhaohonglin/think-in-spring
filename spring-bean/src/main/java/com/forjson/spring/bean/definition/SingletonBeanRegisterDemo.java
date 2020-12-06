package com.forjson.spring.bean.definition;

import com.forjson.spring.bean.instantiation.factory.DefaultUserFactory;
import com.forjson.spring.bean.instantiation.factory.UserFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单体Bean注册
 */
public class SingletonBeanRegisterDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册SpringBean上下文
//        applicationContext.register(BeanInitializationDemo.class);
        //单体Bean声明
        UserFactory singletonUserFactory = new DefaultUserFactory();
        //使用"SingletonBeanRegistry" 注册外部单体Bean
        SingletonBeanRegistry singletonBeanRegistry = applicationContext.getBeanFactory();
        singletonBeanRegistry.registerSingleton("singleton-userFactory", singletonUserFactory);
        //启动Spring上下文
        applicationContext.refresh();
        UserFactory userFactory = applicationContext.getBean("singleton-userFactory", UserFactory.class);
        System.out.println("获取单体Bean UserFactory对象:" + userFactory);
        System.out.println("userFactory == singletonUserFactory:" + (userFactory == singletonUserFactory));
        //关闭Spring上下文
        applicationContext.close();
    }
}
