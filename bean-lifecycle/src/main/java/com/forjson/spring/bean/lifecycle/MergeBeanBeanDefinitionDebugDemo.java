package com.forjson.spring.bean.lifecycle;

import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

/**
 * BeanDefinition的合并Debug演示
 */
public class MergeBeanBeanDefinitionDebugDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //创建PropertiesBeanDefinitionReader 对象
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        //A:需要使用EncodedResource指定编码方式进行加载
        String location = "META-INF/dependency-lookup-context.xml";
        EncodedResource encodedResource = new EncodedResource(new ClassPathResource(location), "UTF-8");
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(encodedResource);

        System.out.println("通过XmlBeanDefinitionReader,加载到bean数量:" + beanNumbers);

        User user = beanFactory.getBean("user", User.class);
        System.out.println("使用EncodedResource加载后,user:" + user);
    }
}
