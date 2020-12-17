package com.forjson.spring.bean.scope;

import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

/**
 * Bean的作用域 演示
 */
public class BeanScopeDemo {

    @Autowired
    private User singletonUser() {
        return new User(System.nanoTime());
    }

    @Autowired
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) //@Scope: 指定Bean的作用域
    private User prototypeUser() {
        return new User(System.nanoTime());
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanScopeDemo.class);
        applicationContext.refresh();
        BeanScopeDemo demo = applicationContext.getBean(BeanScopeDemo.class);
        applicationContext.close();
    }
}
