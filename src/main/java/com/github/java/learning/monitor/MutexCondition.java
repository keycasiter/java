package com.github.java.learning.monitor;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 条件变量
 * created by guanjian on 2020/11/13 9:43
 */
public class MutexCondition {
    /**
     * 互斥条件名称
     */
    private String name;
    /**
     * 互斥条件
     */
    private Condition condition;
    /**
     * 线程个数
     */
    private AtomicInteger count = new AtomicInteger(0);

    MutexCondition(Lock lock, String name) {
        this.condition = lock.newCondition();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public int getCount() {
        return count.get();
    }

    public void setCount(int count) {
        this.count = new AtomicInteger(count);
    }

    public void increaseCount() {
        this.count.incrementAndGet();
    }

    public void decreaseCount() {
        this.count.decrementAndGet();
    }
}
