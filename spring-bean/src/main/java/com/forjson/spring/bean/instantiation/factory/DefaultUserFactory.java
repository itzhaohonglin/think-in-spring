package com.forjson.spring.bean.instantiation.factory;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class DefaultUserFactory implements UserFactory, InitializingBean {


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
}
