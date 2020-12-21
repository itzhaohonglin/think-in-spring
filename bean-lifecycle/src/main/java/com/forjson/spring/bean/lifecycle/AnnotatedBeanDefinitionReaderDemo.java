package com.forjson.spring.bean.lifecycle;

import com.forjson.spring.ioc.container.overview.container.IOCContainerDemo;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 基于 Java注解 {@link org.springframework.context.annotation.AnnotatedBeanDefinitionReader} 演示
 * <p>
 * BeanDefinitionReader 演示 {@link IOCContainerDemo}
 */
public class AnnotatedBeanDefinitionReaderDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();
        //注册当前类 1.此类设计缺陷:没有返回加载到的bean的数量 2.注册类的默认名称:AnnotationBeanNameGenerator
        annotatedBeanDefinitionReader.register(AnnotatedBeanDefinitionReaderDemo.class);
        System.out.println("已加载BeanDefinition 数量:" + (beanFactory.getBeanDefinitionCount() - beanDefinitionCountBefore));
        AnnotatedBeanDefinitionReaderDemo demo = beanFactory.getBean(AnnotatedBeanDefinitionReaderDemo.class);
        System.out.println(demo);
    }
}
