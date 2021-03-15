package com.github.java.learning.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * created by guanjian on 2021/3/15 15:27
 */
public class RateLimiterDemo {

    private final static RateLimiter switchRateLimiter = RateLimiter.create(10, 2, TimeUnit.MILLISECONDS);

    public static void main(String[] args) {
        System.out.format("rate = %s", switchRateLimiter.getRate());
        //尝试获取一个执行的机会，成功返回true，失败的话则立即false，不排队，想排队用acquire
        IntStream.rangeClosed(0,2).forEach(x->{
            switchRateLimiter.tryAcquire();
        });

        if (switchRateLimiter.tryAcquire()) {
            System.out.println("执行");
        } else {
            System.out.println("拦截");
        }
    }
}
