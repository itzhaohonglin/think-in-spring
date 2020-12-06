package com.forjson.spring.bean.definition;

import com.forjson.spring.bean.instantiation.factory.DefaultUserFactory;
import com.forjson.spring.bean.instantiation.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Bean的延迟初始化(lazy-init)  演示
 */
@Configuration
public class BeanLazyInitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanLazyInitializationDemo.class);
        applicationContext.refresh();
        //非延迟初始化 在Spring应用上下文启动后,初始化
        System.out.println("Spring 上下文启动啦~~~~");
        //延迟初始化,当在第一次依赖查找时,初始化
        UserFactory userFactory = applicationContext.getBean(DefaultUserFactory.class);
        applicationContext.close();
        System.out.println(userFactory);
    }

    public static class Config {
        @Bean
        @Lazy
        public DefaultUserFactory userFactory() {
            return new DefaultUserFactory();
        }
    }
}
