package com.github.java.learning.threadlocal;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: <a href=mailto:guanjian@jd.com>guanjian23</a>
 * @date: 2020/11/04 14:29
 * @description: Spring Cloud NetFlix Zuul处理请求使用ThreadLocal进行RequestContext环境隔离保证线程安全
 */
public class RequestContext extends ConcurrentHashMap<String, Object> {

    protected static final ThreadLocal<? extends RequestContext> threadLocal = new ThreadLocal<RequestContext>() {
        @Override
        protected RequestContext initialValue() {
            try {
                return RequestContext.class.newInstance();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    };

    public static RequestContext getCurrentContext() {
        RequestContext context = threadLocal.get();
        return context;
    }
}

