package com.forjson.spring.dependency.lookup;

import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 通过 {@link org.springframework.beans.factory.ObjectProvider} 依赖查找
 *
 * @author howliked
 */
public class ObjectProviderDemo {
    public static void main(String[] args) {

        //创建Spring 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类 ObjectProviderDemo 作为配置类
        applicationContext.register(ObjectProviderDemo.class);
        //启动Spring 容器
        applicationContext.refresh();
        //第一部分:根据beanProvider查找String类型
        lookupBeanProvider(applicationContext);
        //第二部分:1.getIfAvailable使用
        lookupIfAvailable(applicationContext);
        //第二部分:2.获取集合bean对象,并打印
        lookupByStreamOps(applicationContext);
        //关闭Spring容器
        applicationContext.close();
    }

    /**
     * 第一部分:根据beanProvider查找String类型
     */
    private static void lookupBeanProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println("通过getObjectProvider获取String Bean:" + objectProvider.getObject());
    }


    /**
     * 第二部分: 1.获取可用的Bean对象
     *
     * @return
     */
    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        //1. getBeanProvider获取User Bean对象,此类中没有
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        //2. getIfAvailable 获取可用的Bean对象.如果没有,需指定如果创建
        User user = objectProvider.getIfAvailable(User::createUser);
        System.out.println("获取User对象:" + user);
    }

    /**
     * 第二部分: 2.获取多个Bean对象,并使用stream()流式打印出来
     *
     * @return
     */
    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        objectProvider.forEach(System.out::println);

    }


    @Bean
    @Primary
    public String helloWorld() {
        return "Hello,World!";
    }

    @Bean
    public String sayHi() {
        return "Hi~";
    }
}
