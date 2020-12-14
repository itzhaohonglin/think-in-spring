package com.forjson.spring.dependency.injection;

import com.forjson.spring.dependency.injection.domain.UserHolder;
import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于 注解配置Setter注入 演示
 */
public class AnnotationDependencyContructorInjectionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载XML资源,并加载为beanDefinition
        reader.loadBeanDefinitions(location);
        applicationContext.register(AnnotationDependencyContructorInjectionDemo.class);
        applicationContext.refresh();
        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println("userHolder:" + userHolder);
        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
