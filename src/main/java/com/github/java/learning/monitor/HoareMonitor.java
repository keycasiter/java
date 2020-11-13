package com.github.java.learning.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 霍尔管程
 * created by guanjian on 2020/11/13 9:14
 */
public class HoareMonitor {

    private final static Logger LOGGER = LoggerFactory.getLogger(HoareMonitor.class);

    //缓冲池中缓冲区
    static final Integer N = 50;
    //缓冲池
    static List<Integer> buffer = new ArrayList<>(N);

    //互斥锁，用以实现缓冲的互斥访问
    static Lock lock = new ReentrantLock();
    static cond notFull = new cond(lock, "notFull");
    static cond notEmpty = new cond(lock, "notEmpty");
    //用与存放因无法进入管程的阻塞队列&&因为调用signal阻塞自身的线程（Hoare）
    static interf IM = new interf(lock);

    public static void wait(String id, cond declar, interf IM) throws InterruptedException {
        //获取锁，需要在获得锁的情况下才可以操作condition
        declar.count++;
        //LOGGER.info("当前condition中阻塞的线程数：【{},{},{},{}】", IM.enterCount, IM.nextCount, notFull.count, notEmpty.count);

        //判断是否有进程在高优先级队列中
        if (IM.nextCount > 0) {
            //唤醒因调用signal操作的线程
            IM.next.release();
        }
        LOGGER.info("线程【{}】调用wait被挂起到条件变量【{}】。", id, declar.name);
        //挂起时自动释放锁，等待进入管程的队列可以获得锁并进入管程
        declar.condition.await();
        LOGGER.info("被挂起的线程【{}】被唤醒执行。", id);
        declar.count--;
    }

    public static void signal(String id, cond declar, interf IM) throws InterruptedException {
        LOGGER.info("线程【{}】执行了释放资源", id);
        if (declar.count > 0) {
            //挂起自己后，因为调用signal挂起自己的进程数量加1
            IM.nextCount++;
            //唤醒因为条件变量而阻塞的线程
            declar.condition.signal();
            LOGGER.info("唤醒的条件变量为：【{}】", declar.name);
            //LOGGER.info("释放后所有condition中阻塞的线程数：【mutex：{},nextCount:{},notFull:{},notEmpty:{}】", IM.enterCount, IM.nextCount, notFull.count, notEmpty.count);
            //释放资源后，立即把自己挂起,进入高优先级队列-------Hoare的处理方式
            LOGGER.info("线程【{}】调用signal被挂起。", id);
            //释放lock，不然别的线程无法进入管程
            lock.unlock();
            //将当前线程插入到next的阻塞队列中
            IM.next.acquire();
            //再次获取锁-->进入管程
            lock.lock();
            LOGGER.info("被挂起的线程【{}】被唤醒执行。", id);
            //恢复执行后，等待调用的管程的线程数量减1
            IM.nextCount--;
        }
    }

    static class interf {
        //等待着进入管程的队列
        Condition enter;
        //等待着进入管程的阻塞队列中线程的数量
        int enterCount;
        //发出signal的进程挂起自己的信号量，信号量中记录着等待调用管程的进程
        Semaphore next;
        //在next上等待的线程数
        int nextCount;

        interf(Lock lock) {
            enter = lock.newCondition();
            enterCount = 0;
            next = new Semaphore(0);
            nextCount = 0;
        }
    }

    static class cond {
        String name;
        Condition condition;
        int count;

        cond(Lock lock, String id) {
            condition = lock.newCondition();
            count = 0;
            name = id;
        }

    }

    //往缓冲区中投放消息
    public static void putMessage(String id, Integer item) throws InterruptedException {
        lock.lock();
        //如果缓冲池满了，就挂起到notFull的阻塞队列中
        LOGGER.info("执行了投放消息，缓冲池的消息的数量：【{}】", buffer.size());
        while (buffer.size() >= N) {
            LOGGER.info("缓冲池满，线程【{}】阻塞", id);
            wait(id, notFull, IM);
        }
        //保证互斥访问
        //IM.mutex.acquire();
        buffer.add(item);
        //IM.mutex.release();
        signal(id, notEmpty, IM);
        //...     一些别的操作
        lock.unlock();
    }

    //从缓冲区中取消息消费
    public static void getMessage(String id, Integer item) throws InterruptedException {
        //保证互斥访问
        lock.lock();
        //如果缓冲池满了，就挂起到notFull的阻塞队列中
        LOGGER.info("执行了消费消息，缓冲池的消息的数量：【{}】", buffer.size());
        while (buffer.size() <= 0) {
            wait(id, notEmpty, IM);
        }
        item = buffer.remove(0);
        LOGGER.info("消费了一条消息：【{}】", item);
        //IM.mutex.release();
        signal(id, notFull, IM);
        //...     一些别的操作
        lock.unlock();
    }

}
