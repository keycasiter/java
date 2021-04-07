package com.github.java.learning.spring.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * created by guanjian on 2021/4/6 17:42
 */
@Component
public class AsyncListener implements ApplicationListener<EventObject> {

    private final static Logger LOGGER = LoggerFactory.getLogger(AsyncListener.class);

    @Async
    @Override
    public void onApplicationEvent(EventObject eventObject) {
        LOGGER.info("Thread={} 监听事件 source={}", Thread.currentThread().getName(), eventObject.getSource());
    }
}
