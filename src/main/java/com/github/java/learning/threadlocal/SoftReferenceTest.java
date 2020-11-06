package com.github.java.learning.threadlocal;

import java.lang.reflect.Field;
import java.util.stream.Stream;

/**
 * created by guanjian on 2020/11/5 11:44
 */
public class SoftReferenceTest {

    private static void test(String s, boolean isGC) {
        try {
            new ThreadLocal<>().set(s);

            //是否模拟GC
            if (isGC) {
                System.gc();
            }

            //引用关系 Thread ++> currentThread ++> threadLocals.ThreadLocalMap ++> Entry ++> key --> ThreadLocal
            Thread t = Thread.currentThread();

            //解析线程Thread类，利用反射拿到table[]数组，table是一个Entry对象组成的数组
            Class<? extends Thread> clz = t.getClass();
            Field field = clz.getDeclaredField("threadLocals");
            field.setAccessible(true);
            Object threadLocalMap = field.get(t);
            Class<?> tlmClass = threadLocalMap.getClass();
            Field tableField = tlmClass.getDeclaredField("table");
            tableField.setAccessible(true);
            Object[] arr = (Object[]) tableField.get(threadLocalMap);

            Stream.of(arr).forEach(entry -> {
                if (entry != null) {
                    try {
                        Class<?> entryClass = entry.getClass();
                        Field valueField = entryClass.getDeclaredField("value");
                        Field referenceField = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
                        valueField.setAccessible(true);
                        referenceField.setAccessible(true);
                        System.out.println(String.format("弱引用 key : %s, value : %s", referenceField.get(entry), valueField.get(entry)));
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        Thread t = new Thread(() -> test("abc", false));
        t.start();
        t.join();

        System.out.println("==== trigger gc ====");

        Thread t2 = new Thread(() -> test("def", true));
        t2.start();
        t2.join();
    }
}
