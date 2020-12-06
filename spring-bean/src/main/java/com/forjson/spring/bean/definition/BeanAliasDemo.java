package com.forjson.spring.bean.definition;

import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean别名演示
 */
public class BeanAliasDemo {
    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");
        User howlikedUser = beanFactory.getBean("howliked-user", User.class);
        User user = beanFactory.getBean("user", User.class);
        System.out.println("howlikedUser:" + howlikedUser);
        System.out.println("user 和 howlikedUser 是否相等:" + (user == howlikedUser));
    }
}
