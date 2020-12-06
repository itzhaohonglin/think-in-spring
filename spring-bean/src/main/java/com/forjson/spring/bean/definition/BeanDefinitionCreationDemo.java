package com.forjson.spring.bean.definition;

import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 构建演示
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {

        //1.通过 BeanDefinitionBuilder 构建 User Class
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        //设置属性值
        beanDefinitionBuilder.addPropertyValue("id", 1L);
        beanDefinitionBuilder.addPropertyValue("name", "howliked");
        //获取BeanDefinition 并非终态,可以自定义修改
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();


        //2.通过AbstractBeanDefinition以及派生类进行构建
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        //使用MutablePropertyValues 为对象设置值,支持链式调用
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.add("id", 1L).add("name", "howliked");
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);
        System.out.println(beanDefinition.getPropertyValues());
    }
}
