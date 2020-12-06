package com.forjson.spring.bean.instantiation.factory;

import com.forjson.spring.ioc.container.overview.domain.User;

public class HowlikedUserFactory implements UserFactory {

    @Override
    public User createUser() {
        User user = new User();
        user.setId(5L);
        user.setName("howliked");
        return user;
    }
}
