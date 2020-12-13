package com.forjson.spring.ioc.container.overview.container;

import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * AnnotationApplicationContext IOC容器演示
 * 1. 创建应用上下文
 * 2. 注册配置类
 * 3.启动应用上下文
 * 4.使用完成须关闭容器
 */
public class AnnotationApplicationContextAsIoCContainerDemo {

    public static void main(String[] args) {
        //1.创建注解配置应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //2. 注册当前类为 配置类 (Configuration Class)
        applicationContext.register(AnnotationApplicationContextAsIoCContainerDemo.class);
        //3. 启动应用上下文
        applicationContext.refresh();
        //4. 查找集合类型
        lookupCollectionByType(applicationContext);
        //5.关闭容器
        applicationContext.close();
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("集合类型查找:" + users);
        }
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("howliked");
        return user;
    }
}
