package com.github.java.learning.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * created by guanjian on 2021/3/15 15:27
 */
public class RateLimiterDemo {

    private final static RateLimiter switchRateLimiter = RateLimiter.create(10, 10, TimeUnit.MILLISECONDS);

    public static void main(String[] args) {
        RateLimiter r = RateLimiter.create(5);
        while (true) {

            System.out.println("get 1 tokens: " + r.acquire() + "s");
        }
    }
}
