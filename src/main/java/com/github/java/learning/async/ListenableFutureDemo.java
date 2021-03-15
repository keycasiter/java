package com.github.java.learning.async;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Executors;

/**
 * created by guanjian on 2021/3/15 17:08
 */
public class ListenableFutureDemo {

    public static void main(String[] args) {
        // ListeningExecutorService继承jdk的ExecutorService接口，重写了submit方法，修改返回值类型为ListenableFuture
        ListeningExecutorService executor = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        ListenableFuture<String> listenableFuture = executor.submit(() -> {
            Thread.sleep(3000); // 模拟接口调用，耗时ms
            return "hello world";
        });

        //基于addListener：
        listenableFuture.addListener(() -> {
            try {
                System.out.println("异步结果:" + listenableFuture.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, executor);

        //基于addCallback：
        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("异步结果:" + result);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        }, executor);

        System.out.println("end");

    }
}
