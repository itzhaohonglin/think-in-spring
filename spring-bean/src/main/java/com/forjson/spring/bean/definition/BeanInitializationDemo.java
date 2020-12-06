package com.forjson.spring.bean.definition;

import com.forjson.spring.bean.instantiation.factory.DefaultUserFactory;
import com.forjson.spring.bean.instantiation.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean的初始化方式演示
 * 1. @PostConstruct
 * 2. @Bean 指定initMethod属性
 * 3. 实现InitializingBean的afterPropertiesSet()
 */
@Configuration //设置为配置类 Configuration Class
public class BeanInitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();
        UserFactory userFactory = applicationContext.getBean(DefaultUserFactory.class);
        applicationContext.close();
        System.out.println(userFactory);
    }

    public static class Config {
        @Bean(initMethod = "beanInitMethod")
        public DefaultUserFactory userFactory() {
            return new DefaultUserFactory();
        }
    }
}
