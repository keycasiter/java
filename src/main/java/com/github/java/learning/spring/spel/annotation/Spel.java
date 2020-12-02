package com.github.java.learning.spring.spel.annotation;

import com.github.java.learning.spring.spel.Operation;

import java.lang.annotation.*;

/**
 * created by guanjian on 2020/11/30 19:58
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Spel {

    /**
     * 参数列表  --- 解析map类型
     */
    String variables() default "";

    /**
     * 业务ID  --- 解析字符串类型
     */
    String bizId() default "";

    /**
     * 操作类型  --- 枚举类型
     */
    Operation operation();
}
