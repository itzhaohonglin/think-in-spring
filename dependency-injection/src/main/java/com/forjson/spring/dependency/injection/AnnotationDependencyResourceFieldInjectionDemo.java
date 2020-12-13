package com.forjson.spring.dependency.injection;

import com.forjson.spring.dependency.injection.domain.UserHolder;
import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于 注解配置@Resource 属性Field注入 演示
 */
public class AnnotationDependencyResourceFieldInjectionDemo {

    @Resource
    private UserHolder userHolder2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载XML资源,并加载为beanDefinition
        reader.loadBeanDefinitions(location);
        //注册当前类为配置类
        applicationContext.register(AnnotationDependencyResourceFieldInjectionDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        AnnotationDependencyResourceFieldInjectionDemo demo = applicationContext.getBean(AnnotationDependencyResourceFieldInjectionDemo.class);
        UserHolder userHolder2 = demo.userHolder2;
        System.out.println("通过@Resource注解获取userHolder2属性:"+userHolder2);
        //关闭应用上下文
        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
