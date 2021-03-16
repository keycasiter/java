package com.github.java.learning.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * created by guanjian on 2021/3/16 10:23
 */
public class NewScheduledThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newScheduledThreadPool(1);
    }
}
