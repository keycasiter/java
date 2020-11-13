package com.github.java.learning.monitor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 阻塞队列
 * created by guanjian on 2020/11/13 9:42
 */
public class BlockingQueue {
    //等待着进入管程的队列
    Condition enter;
    //等待着进入管程的阻塞队列中线程的数量
    int enterCount;
    //发出signal的进程挂起自己的信号量，信号量中记录着等待调用管程的进程
    Semaphore next;
    //在next上等待的线程数
    int nextCount;

    BlockingQueue(Lock lock) {
        enter = lock.newCondition();
        enterCount = 0;
        next = new Semaphore(0);
        nextCount = 0;
    }
}
