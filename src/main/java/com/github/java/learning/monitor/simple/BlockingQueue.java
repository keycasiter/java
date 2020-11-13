package com.github.java.learning.monitor.simple;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * created by guanjian on 2020/11/13 17:13
 */

public class BlockingQueue<T> {
    final Lock lock = new ReentrantLock();
    // 条件变量：队列不满
    final Condition notFull = lock.newCondition();
    // 条件变量：队列不空
    final Condition notEmpty = lock.newCondition();

    //队列
    List<T> queue = new CopyOnWriteArrayList<>();

    //队列长度
    final static int QUEUE_SIZE = 2;

    // 入队
    public void enq(T x) {
        lock.lock();
        try {
            while (queue.size() >= QUEUE_SIZE) {
                // 等待队列不满
                System.out.format(" %s 阻塞等待\n", Thread.currentThread().getName());
                notFull.await();
            }
            // 省略入队操作...
            queue.add(x);
            System.out.format("%s 执行入队操作\n", Thread.currentThread().getName());

            //入队后,通知可出队
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 出队
    void deq() {
        lock.lock();
        try {
            while (queue.size() <= 0) {
                // 等待队列不空
                notEmpty.await();
            }
            // 出队操作...
            T x = queue.remove(0);
            System.out.format("%s 执行出队操作 \n", Thread.currentThread().getName());

            //出队后，通知可入队
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
