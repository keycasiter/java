package com.github.java.learning.spring.spel;

import org.springframework.stereotype.Component;

/**
 * created by guanjian on 2020/11/30 20:13
 */
@Component
public class SpelService {

    @Spel(variables = "{name:'zhangsan'}")
    public Object test(){
        return null;
    }
}
