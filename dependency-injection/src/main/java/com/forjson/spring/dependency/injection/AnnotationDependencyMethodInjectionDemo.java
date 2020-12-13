package com.forjson.spring.dependency.injection;

import com.forjson.spring.dependency.injection.domain.UserHolder;
import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于 注解的方法Method注入 演示
 */
public class AnnotationDependencyMethodInjectionDemo {

    private UserHolder userHolder;

    private UserHolder userHolder2;

    @Autowired
    private void init1(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    @Autowired
    private void init2(UserHolder userHolder2) {
        this.userHolder2 = userHolder2;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载XML资源,并加载为beanDefinition
        reader.loadBeanDefinitions(location);
        //注册当前类为配置类
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        AnnotationDependencyMethodInjectionDemo demo = applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);
        System.out.println("userHolder:" + demo.userHolder);
        System.out.println("userHolder2:" + demo.userHolder2);
        //关闭应用上下文
        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
