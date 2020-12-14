package com.forjson.spring.dependency.injection;

import com.forjson.spring.dependency.injection.annotation.GroupUser;
import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * 基于 {@link org.springframework.beans.factory.annotation.Qualifier} 注解 演示
 */
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; // bean: SuperUser
    @Autowired
    @Qualifier("user")
    private User assignUser;    //bean:User

    @Autowired
    private Collection<User> allUsers;  //找出两个Bean:user,assignUser

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers;

    @Autowired
    @GroupUser
    private Collection<User> groupUser;

    @Bean
    @Qualifier
    private User user1() {
        return new User(7L);
    }

    @Bean
    @Qualifier
    private User user2() {
        return new User(8L);
    }

    @Bean
    @GroupUser
    private User user3() {
        return new User(9L);
    }

    @Bean
    @GroupUser
    private User user4() {
        return new User(10L);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载XML资源,并加载为beanDefinition
        reader.loadBeanDefinitions(location);
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);
        applicationContext.refresh();
        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);
        //期望是:SuperUser
        System.out.println("使用autowire类型注入获取User Bean:" + demo.user);
        //期望是:User
        System.out.println("使用qualifier注入指定User Bean:" + demo.assignUser);
        //期望是所有User
        System.out.println("输出所有User Bean:" + demo.allUsers);
        //期望是user1、user2、user3、user4
        System.out.println("输出所有@Qualifier User Bean:" + demo.qualifierUsers);
//        期望是user3、user4
        System.out.println("输出所有Group User Bean:" + demo.groupUser);
        applicationContext.close();
    }
}
