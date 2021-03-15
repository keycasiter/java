package com.github.java.learning.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * created by guanjian on 2021/3/15 16:34
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "hello");
        // f2依赖f1的结果做转换
        CompletableFuture<String> f2 = f1.thenApplyAsync(t -> t + " world");

        try {
            System.out.println("异步结果:" + f2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
