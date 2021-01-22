package com.forjson.spring.bean.scope;

import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Collection;

public class BeanScopeDemo {

    @Bean
    private User singletonUser() {
        return new User(System.nanoTime());
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    private User prototypeUser() {
        return new User(System.nanoTime());
    }

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;
    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser2;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;

    @Autowired
    private Collection<User> allUser;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册当前类为配置类
        applicationContext.register(BeanScopeDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        BeanScopeDemo demo = applicationContext.getBean(BeanScopeDemo.class);
        //结论1:singleton作用域 总是共享的同一个变量, prototype作用域 每次访问都会产生一个新的Bean
        System.out.println("singletonUser1:" + demo.singletonUser1);
        System.out.println("singletonUser2:" + demo.singletonUser2);
        System.out.println("prototypeUser1:" + demo.prototypeUser1);
        System.out.println("prototypeUser1:" + demo.prototypeUser2);
        //结论2:当注入的Bean为集合类型时,只会获得2个Bean实例
        System.out.println("allUser:" + demo.allUser);
        //结论3:


        //关闭应用上下文
        applicationContext.close();
    }
}
