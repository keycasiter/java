package com.github.java.learning.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 管程抽象模型
 * <p>
 * get\put方法都是对共享资源操作的过程体，执行过程必须是互斥的
 * wait方法用来阻塞线程
 * signal方法用来唤醒等待队列中的线程
 * lock进行互斥模拟
 * condition是互斥条件，是wait、signal之间的桥梁
 * <p>
 * created by guanjian on 2020/11/13 9:32
 */
public abstract class AbstractMonitor {

    protected final static Logger LOGGER = LoggerFactory.getLogger(HansenMonitor.class);

    //缓冲池的大小
    static final Integer CACHE_SIZE = 5;
    //缓冲池
    static List<Integer> BUFFER = new ArrayList<>(CACHE_SIZE);

    //互斥锁，用以实现缓冲的互斥访问
    Lock lock = new ReentrantLock();

    //条件变量: 队列不满
    MutexCondition notFull = new MutexCondition(lock, "notFull");
    //条件变量: 队列不空
    MutexCondition notEmpty = new MutexCondition(lock, "notEmpty");

    //用与存放因无法进入管程的阻塞队列&&因为调用signal阻塞自身的线程
    BlockingQueue blockingQueue = new BlockingQueue(lock);

    /**
     * 阻塞方法
     *
     * @param id 线程ID
     * @return
     */
    public abstract void wait(String id) throws InterruptedException;

    /**
     * 唤醒方法
     *
     * @param id 线程ID
     * @return
     */
    public abstract void signal(String id) throws InterruptedException;

    /**
     * 获取方法
     *
     * @param id 线程ID
     * @return
     */
    public abstract void get(String id, Integer item) throws InterruptedException;

    /**
     * 设置方法
     *
     * @param id 线程ID
     * @return
     */
    public abstract void put(String id, Integer item) throws InterruptedException;
}
