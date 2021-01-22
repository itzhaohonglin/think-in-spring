package com.forjson.spring.source.debug;

import com.forjson.spring.source.debug.bean.DefaultSingletonBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class SingletonBeanDebugDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerSingleton("singletonBean", new DefaultSingletonBean());
        DefaultSingletonBean bean = beanFactory.getBean(DefaultSingletonBean.class);
        System.out.println("bean:"+bean);
    }
}
