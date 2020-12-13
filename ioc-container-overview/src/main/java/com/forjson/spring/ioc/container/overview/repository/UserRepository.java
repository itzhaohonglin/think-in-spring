package com.forjson.spring.ioc.container.overview.repository;

import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;

import java.util.List;

/**
 * 模拟访问数据库
 */
public class UserRepository {
    private List<User> users;

    private BeanFactory beanFactory;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserRepository{");
        sb.append("users=").append(users);
        sb.append('}');
        return sb.toString();
    }
}
