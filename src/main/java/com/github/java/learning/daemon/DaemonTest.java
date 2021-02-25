package com.github.java.learning.daemon;

/**
 * created by guanjian on 2021/2/25 10:12
 */
public class DaemonTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //设置守护线程，则守护主线程，main方法结束则退出；
        //如果不设置，则为用户线程，阻塞在sleep上
        t1.setDaemon(true);
        t1.start();
    }
}
