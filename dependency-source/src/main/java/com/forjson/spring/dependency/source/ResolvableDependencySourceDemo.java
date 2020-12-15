package com.forjson.spring.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ResolvableDependencySource 演示
 * 只能通过注入方法,不能使用依赖查找 {@link DependencySourceDemo} 演示
 */
public class ResolvableDependencySourceDemo {

    @Autowired
    private String value;

    @PostConstruct
    public void init() {
        System.out.println(value);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //1.注册当前类为 配置类(Configuration Class)
        applicationContext.register(ResolvableDependencySourceDemo.class);
        /**
         * A 解决方案: 添加BeanFactoryPostProcessor
         * 原因:AbstractApplicationContext#refresh() 应用上下文启动时,会先执行BeanFactoryPostProcessor,再执行init()方法
         * 故,在init调用之前,执行我们需要做的操作即可。
         */
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerResolvableDependency(String.class, "Hello,World!");
        });


        //2. 启动应用上下文
        applicationContext.refresh();
        /**
         * Question:
         * 下面两行代码会抛异常:org.springframework.beans.factory.NoSuchBeanDefinitionException
         * 原因是因为Spring生命周期初始化执行完成,才会执行该代码。
         * 在@PostConstruct 执行初始化方法时,需要获取到该注入类型,但此时该类型还没有注入,所以会抛异常.
         * 解决方案:在init之前,将该String类型,注册.见A代码处
         */
//        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
//        beanFactory.registerResolvableDependency(String.class, "Hello,World!");
        //3.关闭应用上下文
        applicationContext.close();
    }
}
