package com.github.java.learning.synchronize;

/**
 * 静态方法持有的锁目标是类
 * 方法块锁目标也是类
 *
 * 持有同一把锁
 *
 * created by guanjian on 2020/11/11 14:18
 */
public class Synchronized03Test {

    public static synchronized void testIntanceMethod(){
        //修饰静态方法，锁定的是Synchronized02Test.class
        try {
            System.out.println("testIntanceMethod执行开始");
            Thread.sleep(5000);
            System.out.println("testIntanceMethod执行结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testStaticBlock(){
        synchronized(Synchronized03Test.class) {
            //修饰方法块的Synchronized03Test类
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
        Synchronized03Test instance = new Synchronized03Test();

        //不是同一把锁，彼此不影响

        new Thread(()->{
            System.out.println("线程-1 访问开始");
            Synchronized03Test.testIntanceMethod();
            System.out.println("线程-1 访问结束");
        }).start();


        new Thread(()->{
            System.out.println("线程-2 访问开始");
            instance.testStaticBlock();
            System.out.println("线程-2 访问结束");
        }).start();
    }
}
