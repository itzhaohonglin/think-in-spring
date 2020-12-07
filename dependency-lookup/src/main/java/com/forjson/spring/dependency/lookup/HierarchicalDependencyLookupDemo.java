package com.forjson.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link org.springframework.beans.factory.HierarchicalBeanFactory} 层次依赖查找 演示
 * {@link ParentsDelegateDependencyLookupDemo} 双亲委派层次依赖查找演示
 */
public class HierarchicalDependencyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalDependencyLookupDemo.class);
        applicationContext.refresh();
        //1. HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("当前 beanFactory 的 parentBeanFactory:" + beanFactory.getParentBeanFactory());
        //2.  ConfigurableListableBeanFactory 定义为可修改的BeanFactory,由此可以设置parentBeanFactory
        //2.1 创建beanFactory
        HierarchicalBeanFactory parentBeanFactory = (HierarchicalBeanFactory) createBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        //3. 使用hierarchicalBeanFactory的containsLocalBean()层次依赖查找bean
        showContainsLocalBean(beanFactory, "user");
        showContainsLocalBean(parentBeanFactory, "user");
        applicationContext.close();
    }


    private static void showContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前Bean:%s,是否包含[name:%s]:", beanFactory, beanName, beanFactory.containsLocalBean(beanName));
    }


    private static BeanFactory createBeanFactory() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        String location = "classpath:META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        return defaultListableBeanFactory;
    }

}
