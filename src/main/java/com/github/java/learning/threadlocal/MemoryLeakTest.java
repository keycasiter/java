package com.github.java.learning.threadlocal;

import java.util.stream.IntStream;

/**
 * @author: <a href=mailto:guanjian@jd.com>guanjian23</a>
 * @date: 2020/11/04 14:50
 * @description: 模拟TheadLocal内存泄露
 */
public class MemoryLeakTest {

    static class TestClass {
        private int id;
        private int[] arr;
        private ThreadLocal<TestClass> threadLocal;

        TestClass(int id) {
            this.id = id;
            arr = new int[1000000];
            threadLocal = new ThreadLocal<>();
            threadLocal.set(this);
        }

        public void printId() {
            System.out.println(threadLocal.get().id);
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            IntStream.rangeClosed(0,10000).forEach(x->{
                TestClass t = new TestClass(x);
                t.printId();
                //调用t=null后，虽然无法再通过t访问内存地址，但是当前线程依旧存活，
                // 可以通过thread指向的内存地址，访问到Thread对象，从而访问到ThreadLocalMap对象，
                // 访问到value指向的内存空间，访问到arr指向的内存空间，
                // 从而导致Java垃圾回收并不会回收int[1000000]@541这一片空间。
                // 那么随着循环多次之后，不被回收的堆空间越来越大，最后抛出java.lang.OutOfMemoryError: Java heap space
                System.out.println(t.threadLocal.toString());
                t = null;
                //用完直接移除不会出现问题
                //t.threadLocal.remove();
            });
        }).start();
    }
}

