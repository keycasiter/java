package com.github.java.learning.synchronize;

/**
 * 静态方法持有的锁目标是类
 * 方法块实例是对象
 *
 * 彼此不冲突
 *
 * created by guanjian on 2020/11/11 14:18
 */
public class Synchronized02Test {

    public static synchronized void testStaticMethod(){
        //修饰静态方法，锁定的是Synchronized02Test.class
        try {
            System.out.println("testStaticMethod执行开始");
            Thread.sleep(5000);
            System.out.println("testStaticMethod执行结束");
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
        Synchronized02Test instance = new Synchronized02Test();

        //不是同一把锁，彼此不影响

        new Thread(()->{
            System.out.println("线程-1 访问开始");
            Synchronized02Test.testStaticMethod();
            System.out.println("线程-1 访问结束");
        }).start();


        new Thread(()->{
            System.out.println("线程-2 访问开始");
            instance.testStaticBlock();
            System.out.println("线程-2 访问结束");
        }).start();
    }
}
