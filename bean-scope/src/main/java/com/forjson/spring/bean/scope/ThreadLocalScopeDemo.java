package com.forjson.spring.bean.scope;

import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * 自定义 {@link ThreadLocalScope} 演示
 */
public class ThreadLocalScopeDemo {


    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public User user() {
        return new User(System.nanoTime());
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ThreadLocalScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });
        applicationContext.refresh();
        for (int i = 0; i < 5; i++) {
            //有点类似singleton 作用域,获取5次user,是同一个对象
//            System.out.printf("当前线程ID:[%s],获取user Bean:%s%n", Thread.currentThread().getId(), applicationContext.getBean("user", User.class));
            //启动5个线程: 5个线程分别获取到不同的对象
            Thread thread = new Thread(() -> {
                System.out.printf("当前线程ID:[%s],获取user Bean:%s%n", Thread.currentThread().getId(), applicationContext.getBean("user", User.class));
            });
            thread.start();

            //强制线程全部执行完成
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        applicationContext.close();
    }
}
