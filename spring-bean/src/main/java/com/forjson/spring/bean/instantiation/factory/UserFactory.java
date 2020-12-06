package com.forjson.spring.bean.instantiation.factory;

import com.forjson.spring.ioc.container.overview.domain.User;

public interface UserFactory {
    default public User createUser() {
        User user = new User();
        user.setId(3L);
        user.setName("user-factory");
        return user;
    }
}
