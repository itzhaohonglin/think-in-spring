package com.forjson.spring.bean.instantiation;

import com.forjson.spring.bean.instantiation.factory.DefaultUserFactory;
import com.forjson.spring.bean.instantiation.factory.UserFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 特殊方式实例化Bean
 * {@link ServiceLoader} 只能通过接口的方法,指定实例化的Bean
 * <p>
 * 1. 使用Java SPI Demo {@link ServiceLoader}
 * 2. {@link org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean} 创建
 */
public class SpecialBeanInstantiationDemo {
    public static void main(String[] args) {
        //1.使用Java SPI ServiceLoader
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        showServiceLoader(serviceLoader);
        //2.使用Spring的ServiceLoaderFactoryBean实例化Bean
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
        ServiceLoader<UserFactory> serviceLoaderFactoryBean = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        showServiceLoader(serviceLoaderFactoryBean);

        //3.使用AutowireCapableBeanFactory 实例化
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        UserFactory userFactory = autowireCapableBeanFactory.createBean(DefaultUserFactory.class);
        System.out.println("使用autowireCapableBeanFactory创建userFactory:" + userFactory.createUser());

    }


    /**
     * 使用ServiceLoader实例化
     */
    public static void showServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            System.out.println("serviceLoader userFactory:" + iterator.next().createUser());
        }

    }

}
