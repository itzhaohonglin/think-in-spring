package com.forjson.spring.bean.lifecycle;

import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

/**
 * {@link org.springframework.beans.factory.support.BeanDefinitionReader}
 *
 * @see com.forjson.spring.ioc.container.overview.container.IOCContainerDemo
 * <p>
 * Bean  元数据配置 {@link PropertiesBeanDefinitionReader} 演示
 */
public class BeanMetadataConfigurationDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //创建PropertiesBeanDefinitionReader 对象
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        //指定配置文件位置
//        String location = "classpath:/META-INF/user.properties";
        //加载beanDefinition,返回加载到的bean的数量
        //Q:properties默认使用ASCII编码加载,若properties包含中文则会乱码(loadBeanDefinitions(String))
//        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(location);
//        System.out.println("通过PropertiesBeanDefinitionReader,加载到bean数量:" + beanNumbers);
//        User user = beanFactory.getBean("user", User.class);
//        System.out.println(user);
        //A:需要使用EncodedResource指定编码方式进行加载
        String location = "META-INF/user.properties";
        EncodedResource encodedResource = new EncodedResource(new ClassPathResource(location), "UTF-8");
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(encodedResource);

        System.out.println("通过PropertiesBeanDefinitionReader,加载到bean数量:" + beanNumbers);

        User user = beanFactory.getBean("user", User.class);
        System.out.println("使用EncodedResource加载后,user:"+user);

    }
}
