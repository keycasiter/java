package com.github.java.learning;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * created by guanjian on 2020/11/4 17:07
 */
public class ApiTest {

    //测试2的幂次方-1数据二进制展示
    public static void test01() {
        IntStream.rangeClosed(0, 30).forEach(x -> {
            int num = new Double(Math.pow(2, x)).intValue() - 1;
            System.out.format("\t 2的%s次方 num:%s \t binary:%s \n",x, num, Long.toBinaryString(num));
        });
    }


    public static void main(String[] args) {
//        Thread t1 = new Thread(()->{
//            try {
//                Thread.sleep(10000000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        //设置守护线程，则守护主线程，main方法结束则退出；
//        //如果不设置，则为用户线程，阻塞在sleep上
//        t1.setDaemon(true);
//        t1.start();
        int a = 10;
        System.out.println(a^a);
        System.out.println(a^0);
        System.out.println(a^a^a);
    }
}
