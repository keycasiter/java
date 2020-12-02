package com.github.java.learning.spring.aop.annotation;

import com.github.java.learning.spring.spel.Operation;

import java.lang.annotation.*;

/**
 * created by guanjian on 2020/11/30 19:58
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Aops {

    Aop[] value();
}
