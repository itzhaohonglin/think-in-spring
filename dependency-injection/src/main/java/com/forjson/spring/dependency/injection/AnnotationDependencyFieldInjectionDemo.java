package com.forjson.spring.dependency.injection;

import com.forjson.spring.dependency.injection.domain.UserHolder;
import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于 注解配置属性Field注入 演示
 */
public class AnnotationDependencyFieldInjectionDemo {

    @Autowired
    private UserHolder userHolder;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载XML资源,并加载为beanDefinition
        reader.loadBeanDefinitions(location);
        //注册当前类为配置类
        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        AnnotationDependencyFieldInjectionDemo demo = applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);
        UserHolder userHolder = demo.userHolder;
        System.out.println("userHolder:"+userHolder);
        //Spring Bean默认是单例,故 获取到的bean都同一个实例
        UserHolder userHolderBean = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder == userHolderBean);
        //关闭应用上下文
        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
