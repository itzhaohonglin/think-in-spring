package com.forjson.spring.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖来源  演示
 * <p>
 * 1. 调用{@link org.springframework.context.support.AbstractApplicationContext#refresh}时,prepareBeanFactory(beFactory)方法
 * 注入了4个对象 (2个Bean -> beanFactory 和 当前应用上下文)
 * beanFactory.registerResolvableDependency(BeanFactory.class, beanFactory);
 * beanFactory.registerResolvableDependency(ResourceLoader.class, this);
 * beanFactory.registerResolvableDependency(ApplicationEventPublisher.class, this);
 * beanFactory.registerResolvableDependency(ApplicationContext.class, this);
 */
public class DependencySourceDemo {

    @Autowired
    private BeanFactory beanFactory;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 注入方法可以获取到
     */
    @PostConstruct
    public void initByInjection() {
        System.out.println("beanFactory == applicationContext:" + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext:" + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader == applicationContext:" + (resourceLoader == applicationContext));
        System.out.println("applicationEventPublisher == applicationContext:" + (applicationEventPublisher == applicationContext));
    }

    /**
     * 依赖类型查找获取不到
     */
    @PostConstruct
    public void initByLookup() {
        getBean(BeanFactory.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
        getBean(ApplicationContext.class);
    }

    public <T> T getBean(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("当前类型" + beanType.getName() + "无法在BeanFactory中被找到");
        }
        return null;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //1.注册当前类为 配置类(Configuration Class)
        applicationContext.register(DependencySourceDemo.class);
        //2. 启动应用上下文
        applicationContext.refresh();
        //3.关闭应用上下文
        applicationContext.close();
    }

}
