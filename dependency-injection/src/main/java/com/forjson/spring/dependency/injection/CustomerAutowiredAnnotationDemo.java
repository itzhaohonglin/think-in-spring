package com.forjson.spring.dependency.injection;

import com.forjson.spring.dependency.injection.annotation.InjectObject;
import com.forjson.spring.dependency.injection.annotation.MyAutowired;
import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.Optional;

/**
 * 自定义{@link org.springframework.beans.factory.annotation.Autowired} 实现注入
 * 一、自定义注解 {@link MyAutowired} 直接复用@Autowired
 * 二. 自定义注解 @InjectObject 实现@Autowired功能
 */
public class CustomerAutowiredAnnotationDemo {

    @MyAutowired
    private Optional<User> user;
    @InjectObject
    private Optional<User> customerUser;


    /**
     * -1 此时对象都会为空,因为{@link org.springframework.context.annotation.AnnotationConfigUtils#registerAnnotationConfigProcessors} 逻辑
     * 若BeanDefinitionRegistry中不包含{@link org.springframework.context.annotation.AnnotationConfigUtils#AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME} 则创建根节点
     * 解决方案：
     * 1.创建bean时添加static关键字,脱离Spring的生命周期，此时其他自动注入方式失效,如@Resource、@Inject
     * 2.将原先的注解,注入进行添加进入  Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>();
     *
     * @return
     */
//    @Bean
//    public
//     static       //1.此处可解决对象均为null的问题
//    AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        //2. 将原先的注解,包含进来,使用setAutowiredAnnotationTypes()方法;
////        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType(InjectObject.class);
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>();
//        autowiredAnnotationTypes.add(Autowired.class);
//        autowiredAnnotationTypes.add(InjectObject.class);
//        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
//        return autowiredAnnotationBeanPostProcessor;
//    }

    /**
     * {@link AutowiredAnnotationBeanPostProcessor#order}
     *
     * @return
     */
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)  //优先级
    public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType(InjectObject.class);
        return autowiredAnnotationBeanPostProcessor;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载XML资源,并加载为beanDefinition
        reader.loadBeanDefinitions(location);
        applicationContext.register(CustomerAutowiredAnnotationDemo.class);
        applicationContext.refresh();
        CustomerAutowiredAnnotationDemo demo = applicationContext.getBean(CustomerAutowiredAnnotationDemo.class);
        System.out.println(demo.user);
        System.out.println(demo.customerUser);
        applicationContext.close();
    }
}
