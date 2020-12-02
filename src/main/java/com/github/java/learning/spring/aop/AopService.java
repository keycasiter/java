package com.github.java.learning.spring.aop;

import com.github.java.learning.spring.aop.annotation.Aop;
import com.github.java.learning.spring.spel.Operation;
import com.github.java.learning.spring.spel.User;
import com.github.java.learning.spring.spel.annotation.Spel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * created by guanjian on 2020/11/30 20:13
 */
@Component("aopService")
public class AopService {

    @Aop("a")
    @Aop("c")
    @Aop("b")
    public Object test(User req) {
        return new Object();
    }
}
