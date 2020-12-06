package com.forjson.spring.bean.definition;

import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * 注解 BeanDefinition 演示
 */
@Import(value = AnnotationBeanDefinitionDemo.Config.class)      //3.通过@Import注册Bean
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        //1. 创建beanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 1.1通过@bean获取User对象
        applicationContext.register(Config.class);
//        User beanUser = applicationContext.getBean(User.class);
//        System.out.println("beanUser :" + beanUser);
        applicationContext.refresh();
        //2 通过@Component 方式注入获取User对象
        User componentUser = applicationContext.getBean(User.class);
        System.out.println("componentUser :" + componentUser);
        //3 通过@Import方式获取User对象
//        User importUser = applicationContext.getBean(User.class);
//        System.out.println("importUser :" + importUser);

        applicationContext.close();

    }

    @Component  //2通过@Component
    public static class Config {

        @Bean(value = {"user", "howliked-user"}) //通过Bean
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("howliked");
            return user;
        }
    }
}
