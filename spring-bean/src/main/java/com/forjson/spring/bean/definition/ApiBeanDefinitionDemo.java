package com.forjson.spring.bean.definition;

import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Api BeanDefinition 演示
 */
public class ApiBeanDefinitionDemo {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ApiBeanDefinitionDemo.class);
        applicationContext.refresh();
        //设置属性.
        Map<String, Object> propertyValues = new HashMap<>();
        propertyValues.put("id", 1L);
        propertyValues.put("name", "howliked");
        //命名方式创建
        registerBeginDefinition(applicationContext, "user", propertyValues, User.class);
        //非命名方式创建
        registerBeginDefinition(applicationContext, null, propertyValues, User.class);
        System.out.println("获取User bean 对象集合:" + applicationContext.getBeansOfType(User.class));
        applicationContext.close();

    }

    /**
     * @param registry
     * @param beanName
     * @param propertyValues
     * @param className
     */
    public static void registerBeginDefinition(BeanDefinitionRegistry registry, String beanName, Map<String, Object> propertyValues, Class<?> className) {
        //1. 创建BeanDefinitionBuidler
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(className);
        //2. 设置属性和值
        propertyValues.forEach(beanDefinitionBuilder::addPropertyValue);
        //3.判断是否使用命名方式构建
        //3.1 使用命名方式构建
        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {    //3.2 非命名方式构建
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }

    }
}
