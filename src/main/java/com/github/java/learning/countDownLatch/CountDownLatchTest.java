package com.github.java.learning.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * created by guanjian on 2020/12/28 11:19
 */
public class CountDownLatchTest {

    private static final CountDownLatch cdl = new CountDownLatch(3);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始");

        new Thread(() -> {
            try {
                System.out.format("当前计数=%s,需等待 \n", cdl.getCount());
                Thread.sleep(1000);
                cdl.countDown();
                System.out.format("当前计数=%s,需等待 \n", cdl.getCount());
                Thread.sleep(1000);
                cdl.countDown();
                System.out.format("当前计数=%s,需等待 \n", cdl.getCount());
                Thread.sleep(1000);
                cdl.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        cdl.await(6000, TimeUnit.MILLISECONDS);
        print();
        System.out.println("结束");
    }

    private static void print() {
        System.out.println("执行了");
    }
}
