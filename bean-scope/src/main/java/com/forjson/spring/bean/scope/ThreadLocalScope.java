package com.forjson.spring.bean.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class ThreadLocalScope implements Scope {

    /**
     * 取出scope的值
     *
     * @param name
     * @param objectFactory
     * @return
     */
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        return null;
    }

    /**
     * 移除scope
     *
     * @param name
     * @return
     */
    @Override
    public Object remove(String name) {
        return null;
    }

    /**
     * 注册销毁回调
     *
     * @param name
     * @param callback
     */
    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    /**
     * 获取上下文的值
     *
     * @param key
     * @return
     */
    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    /**
     * 获取会话中的id
     *
     * @return
     */
    @Override
    public String getConversationId() {
        return null;
    }
}
