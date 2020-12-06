package com.forjson.spring.dependency.lookup;

import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

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
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        System.out.println("通过getObjectProvider获取UserBean:" + userObjectProvider.getObject());
        //关闭Spring容器
        applicationContext.close();
    }

    @Bean
    public static User helloWorld() {
        User user = new User(1L, "howliked");
        return user;
    }
}
