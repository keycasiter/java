package com.github.java.learning.spring.spel;

import com.github.java.learning.spring.spel.annotation.Spel;
import org.springframework.stereotype.Component;

/**
 * created by guanjian on 2020/11/30 20:13
 */
@Component
public class SpelService {

    @Spel(variables = "{name:'zhangsan'}")
    public Object test(){
        return new Object();
    }
}
