package com.forjson.spring.bean.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Scope:thread-local 作用域
 */
public class ThreadLocalScope implements Scope {

    public static final String SCOPE_NAME = "thread-local";

    private NamedThreadLocal<Map<String, Object>> threadLocal = new NamedThreadLocal(ThreadLocalScope.SCOPE_NAME) {
        @Override
        protected Object initialValue() {
            return new HashMap<>();
        }
    };

    /**
     * 取出scope的值
     *
     * @param name
     * @param objectFactory
     * @return
     */
    @Override

    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> context = getContext();
        Object o = context.get(name);
        if (o == null) {
            o = objectFactory.getObject();
            context.put(name, o);
        }
        return o;
    }

    /**
     * 获取应用上下文;
     *
     * @return
     */
    private Map<String, Object> getContext() {
        return threadLocal.get();
    }

    /**
     * 移除scope
     *
     * @param name
     * @return
     */
    @Override
    public Object remove(String name) {
        Map<String, Object> context = getContext();
        return context.remove(name);
    }

    /**
     * 注册销毁回调
     *
     * @param name
     * @param callback
     */
    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

        //todo
    }

    /**
     * 获取上下文的值
     *
     * @param key
     * @return
     */
    @Override
    public Object resolveContextualObject(String key) {
        Map<String, Object> context = getContext();
        return context.get(key);
    }

    /**
     * 获取会话中的id
     *
     * @return
     */
    @Override
    public String getConversationId() {

        return String.valueOf(Thread.currentThread().getId());
    }
}
