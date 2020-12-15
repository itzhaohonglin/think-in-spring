package com.forjson.spring.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * 外部化配置作为依赖来源 演示
 */
@Configuration  //注册配置类
@PropertySource(value = "classpath:/META-INF/config.properties", encoding = "UTF-8") //@PropertySource 注解:引入外部配置文件
public class ExternalConfigurationDependencySourceDemo {

    @Value("${config.id:-1}") //@Value 读取外部配置文件,写法:${key:defaultValue}
    private Long id;
    @Value("${config.name:defaultName}")
    private String name;
    @Value("${config.resource:defaultResource}")
    private Resource resource;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册当前类为 配置类(Configuration Class)
        applicationContext.register(ExternalConfigurationDependencySourceDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        ExternalConfigurationDependencySourceDemo demo = applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);
        System.out.println("config.id=" + demo.id);
        System.out.println("config.name=" + demo.name);
        System.out.println("config.resource=" + demo.resource);
        //关闭应用上下文
        applicationContext.close();
    }
}
