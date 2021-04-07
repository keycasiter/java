package com.github.java.learning.spring.async;

import org.springframework.context.ApplicationEvent;

/**
 * created by guanjian on 2021/4/6 17:46
 */
public class EventObject extends ApplicationEvent {

    public EventObject(Object source) {
        super(source);
    }
}
