package com.forjson.spring.ioc.container.overview.domain;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class User implements InitializingBean, DisposableBean {
    private Long id;
    private String name;

    /**
     * bean-scope create
     */
    @Autowired
    private BeanFactory beanFactory;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static User createUser() {
        User user = new User();
        user.setId(2L);
        user.setName("static-method-instantiation");
        return user;
    }

    @Override
    public void destroy() throws Exception {
        System.out.printf("当前Bean: %s 正在销毁... \n", this.getClass().getName());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.printf("当前Bean: %s 正在初始化...\n", this.getClass().getName());
    }
}
