package com.github.java.learning.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * created by guanjian on 2021/3/15 19:40
 */
public class NewCachedThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        IntStream.rangeClosed(0,10).forEach(x->{
            executor.submit(()->{
                System.out.format("执行 i=%s \n", x);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        executor.shutdown();
    }
}
