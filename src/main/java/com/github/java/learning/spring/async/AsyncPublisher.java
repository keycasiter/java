package com.github.java.learning.spring.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * created by guanjian on 2021/4/6 17:40
 */
@Component
public class AsyncPublisher implements ApplicationEventPublisherAware {

    private final static Logger LOGGER = LoggerFactory.getLogger(AsyncPublisher.class);

    @Resource
    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void publish(EventObject eventObject) {
        LOGGER.info("Thread={} 发布事件 source={}",Thread.currentThread().getName(), eventObject.getSource());
        this.publisher.publishEvent(eventObject);
    }
}
