package com.github.java.learning.monitor.simple;

/**
 * created by guanjian on 2020/11/13 17:26
 */
public class ConsumerThread extends Thread {

    private BlockingQueue<Integer> blockingQueue;

    public ConsumerThread(String name, BlockingQueue blockingQueue) {
        super.setName(name);
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        blockingQueue.deq();
    }
}
