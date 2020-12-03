package com.forjson.spring.ioc.container.overview.repository;

import com.forjson.spring.ioc.container.overview.domain.User;

import java.util.List;

/**
 * 模拟访问数据库
 */
public class UserRepository {
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
