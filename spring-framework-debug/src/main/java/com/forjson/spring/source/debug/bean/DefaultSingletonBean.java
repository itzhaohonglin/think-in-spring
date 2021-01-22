package com.forjson.spring.source.debug.bean;

import java.util.Date;

/**
 * 自定义的单例Bean
 */
public class DefaultSingletonBean {
    private Integer id;
    private String username;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DefaultSingletonBean{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
