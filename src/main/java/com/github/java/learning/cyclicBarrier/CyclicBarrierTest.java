package com.github.java.learning.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * created by guanjian on 2020/12/28 17:57
 */
public class CyclicBarrierTest {

    private final static int nums = 5;

    private final static CyclicBarrier cyclicBarrier = new CyclicBarrier(nums, () -> {
        System.out.println("执行");
    });

    public static void main(String[] args) {
        IntStream.range(0, nums).forEach(x -> {
            new Thread(()->{
                try {
                    System.out.println("阻塞等待");
                    cyclicBarrier.await(6000, TimeUnit.MILLISECONDS);
                    System.out.println("满足条件执行");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
