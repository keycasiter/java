package com.github.java.learning.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

/**
 * created by guanjian on 2020/12/28 15:31
 */
public class SemaphoreTest {

    //模拟汽车准乘人数
    private final static Semaphore semaphore = new Semaphore(5);

    //A旅行团人数5人
    private final static int A_NUMS = 5;
    //B旅行团人数5人
    private final static int B_NUMS = 5;

    public static void main(String[] args) throws InterruptedException {
        //A旅行团上车
        new Thread(() -> {
            IntStream.range(0,A_NUMS).forEach(x->{
                try {
                    System.out.format("当前汽车准乘人数=%s \n", semaphore.availablePermits());
                    semaphore.acquireUninterruptibly();
                    System.out.println("A旅行团上车1人 \n");
                    Thread.sleep(new Random().nextInt(3000));
                    System.out.format("剩余汽车准乘人数=%s \n", semaphore.availablePermits());
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }).start();

        //B旅行团上车
        new Thread(() -> {
            IntStream.range(0,B_NUMS).forEach(x->{
                try {
                    System.out.format("当前汽车准乘人数=%s \n", semaphore.availablePermits());
                    semaphore.acquireUninterruptibly();
                    System.out.println("B旅行团上车1人 \n");
                    Thread.sleep(new Random().nextInt(3000));
                    System.out.format("剩余汽车准乘人数=%s \n", semaphore.availablePermits());
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }).start();
    }
}
