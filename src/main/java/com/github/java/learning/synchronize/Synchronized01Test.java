package com.github.java.learning.synchronize;

/**
 * 修饰实例方法
 * 修饰实例对象的方法块
 *
 * 是同一把锁
 *
 * created by guanjian on 2020/11/11 14:18
 */
public class Synchronized01Test {

    public synchronized void testIntanceMethod(){
        //修饰实例方法，锁定的是this，即当前SynchronizedTest对象
        try {
            System.out.println("testIntanceMethod执行开始");
            Thread.sleep(5000);
            System.out.println("testIntanceMethod执行结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testStaticBlock(){
        synchronized(this) {
            //修饰方法块的this，锁定的是this，即当前SynchronizedTest对象
            System.out.println("testStaticBlock执行开始");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("testStaticBlock执行结束");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Synchronized01Test instance = new Synchronized01Test();

        //由于是使用instance对象这同一把锁，因此先持有线程先执行，另一个线程需要等待执行完毕释放锁后方可继续执行

        new Thread(()->{
            System.out.println("线程-1 访问开始");
            instance.testIntanceMethod();
            System.out.println("线程-1 访问结束");
        }).start();


        new Thread(()->{
            System.out.println("线程-2 访问开始");
            instance.testStaticBlock();
            System.out.println("线程-2 访问结束");
        }).start();
    }
}
