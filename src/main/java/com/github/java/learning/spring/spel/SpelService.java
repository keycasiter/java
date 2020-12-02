package com.github.java.learning.spring.spel;

import com.github.java.learning.spring.spel.annotation.Spel;
import org.springframework.stereotype.Component;

/**
 * created by guanjian on 2020/11/30 20:13
 */
@Component("spelService")
public class SpelService {

    @Spel(
            variables = "{name:'#req.name',age:'#req.age'}",
            bizId = "#req.bizId",
            operation = Operation.START

    )
    public Object test(User req) {
        return new Object();
    }
}
