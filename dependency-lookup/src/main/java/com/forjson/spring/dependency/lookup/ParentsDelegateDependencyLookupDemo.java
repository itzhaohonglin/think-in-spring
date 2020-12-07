package com.forjson.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link org.springframework.beans.factory.HierarchicalBeanFactory} 双亲委派依赖查找演示
 * 当前beanFactory以及父类是否包含某一个beanName
 * {@link ConfigurableListableBeanFactory} 可修改的集合类型BeanFactory
 */
public class ParentsDelegateDependencyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalDependencyLookupDemo.class);
        applicationContext.refresh();
        //1. 创建BeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        //2. 设置该bean的parentBeanFactory
        HierarchicalBeanFactory parentBeanFactory = createBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        //3.查询当前beanFactory中是否包含"user" bean
        System.out.printf("该beanFactory:%s,是否包含指定beanName:%s", beanFactory, containsBean(beanFactory, "user"));
        applicationContext.close();
    }

    /**
     * @param beanFactory 当前beanFactory
     * @param beanName    要获取的beanName
     */
    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory hierarchinalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            //递归调用:从该beanFactory的父类开始查找
            if (containsBean(hierarchinalBeanFactory, beanName)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }

    private static HierarchicalBeanFactory createBeanFactory() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        String location = "classpath:META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        return defaultListableBeanFactory;
    }
}
