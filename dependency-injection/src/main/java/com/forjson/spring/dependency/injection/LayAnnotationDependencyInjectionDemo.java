package com.forjson.spring.dependency.injection;

import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

/**
 * 基于 {@link ObjectProvider} 延迟注入 演示
 */
public class LayAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;  //实时注入
    @Autowired
    private ObjectProvider<User> userObjectProvider;    //延迟注入(推荐)
    @Autowired
    private ObjectFactory<Set<User>> usersObjectFactory;  //延迟注入

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载XML资源,并加载为beanDefinition
        reader.loadBeanDefinitions(location);
        applicationContext.register(LayAnnotationDependencyInjectionDemo.class);
        applicationContext.refresh();
        LayAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LayAnnotationDependencyInjectionDemo.class);
        //期望:SuperUser Bean
        System.out.println("使用objectProvider延迟注入获取User Bean:" + demo.userObjectProvider.getObject());
        // ObjectProvider可以直接循环读取
        demo.userObjectProvider.forEach(System.out::println);
        //期望:2 User Bean -> SuperUser & User Bean
        System.out.println("使用objectFactory延迟注入获取User Bean:" + demo.usersObjectFactory.getObject());
        applicationContext.close();
    }
}
