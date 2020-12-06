package com.forjson.spring.bean.instantiation.factory;

import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * 实现 {@link FactoryBean} 实例化 User Bean
 */
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        User user = new User();
        user.setId(4L);
        user.setName("user-factory-bean");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
