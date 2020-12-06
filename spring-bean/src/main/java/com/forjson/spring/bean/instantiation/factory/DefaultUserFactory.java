package com.forjson.spring.bean.instantiation.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {


    /**
     * @since BeanInitialization
     */

    @PostConstruct
    public void postConstructInitMethod() {
        System.out.println("@PostConstruct initMethod 开始初始化了~~~~");
    }

    public void beanInitMethod() {
        System.out.println("@Bean initMethod 开始初始化了~~~~");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean afterPropertiesSet() 开始初始化了~~~~");

    }


    @PreDestroy
    public void preDesstroyMethod() {
        System.out.println("@PreDestroy的preDesstroyMethod() 开始销毁了!!!");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean的destroy 开始销毁了!!!");
    }

    public void customerDestroyMethod() {
        System.out.println("@Bean的destroyMethod 开始销毁了!!!!");
    }


    @Override
    protected void finalize() throws Throwable {
        System.out.println("DefaultUserFactory对象正在被GC回收....");
    }
}
