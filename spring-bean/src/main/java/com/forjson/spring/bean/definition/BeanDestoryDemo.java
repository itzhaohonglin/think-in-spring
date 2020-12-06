package com.forjson.spring.bean.definition;

import com.forjson.spring.bean.instantiation.factory.DefaultUserFactory;
import com.forjson.spring.bean.instantiation.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean的初始化方式演示
 * 1. @PreDestory
 * 2. 实现DisposableBean的destory()方法
 * 3. @Bean注解的destroyMethod属性
 */
@Configuration //设置为配置类 Configuration Class
public class BeanDestoryDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanDestoryDemo.class);
        applicationContext.refresh();
        UserFactory userFactory = applicationContext.getBean(DefaultUserFactory.class);
        System.out.println("application容器准备关闭...");
        applicationContext.close();
        System.out.println("application容器关闭了...");
        System.out.println(userFactory);
    }

    public static class Config {
        @Bean(destroyMethod = "customerDestroyMethod")
        public DefaultUserFactory userFactory() {
            return new DefaultUserFactory();
        }
    }
}
