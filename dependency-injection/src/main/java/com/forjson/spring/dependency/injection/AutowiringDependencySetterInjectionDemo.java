package com.forjson.spring.dependency.injection;

import com.forjson.spring.dependency.injection.domain.UserHolder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 自动模式注入演示
 * 使用自动模式注入，在autowiring-dependency-setter-injection.xml中为要自动注入的bean添加auto-wire 标签
 * auto-wire常用取值: byName / byType
 */
public class AutowiringDependencySetterInjectionDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/autowiring-dependency-setter-injection.xml");
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println("userHolder:" + userHolder);
    }
}
