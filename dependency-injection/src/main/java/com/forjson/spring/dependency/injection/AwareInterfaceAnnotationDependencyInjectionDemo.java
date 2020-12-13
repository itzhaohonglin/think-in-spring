package com.forjson.spring.dependency.injection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于{@link org.springframework.beans.factory.Aware} 回调注入 演示
 */
public class AwareInterfaceAnnotationDependencyInjectionDemo implements BeanFactoryAware, ApplicationContextAware {
    private static BeanFactory beanFactory;
    private static ApplicationContext applicationContext;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AwareInterfaceAnnotationDependencyInjectionDemo.class);
        applicationContext.refresh();

        System.out.println(AwareInterfaceAnnotationDependencyInjectionDemo.applicationContext == applicationContext);
        System.out.println(AwareInterfaceAnnotationDependencyInjectionDemo.beanFactory == applicationContext.getBeanFactory());

        applicationContext.close();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareInterfaceAnnotationDependencyInjectionDemo.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInterfaceAnnotationDependencyInjectionDemo.applicationContext = applicationContext;
    }
}
