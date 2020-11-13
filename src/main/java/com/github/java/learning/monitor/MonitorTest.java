package com.github.java.learning.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * created by guanjian on 2020/11/13 9:32
 */
public class MonitorTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(MonitorTest.class);

    private static AbstractMonitor monitor = new HansenMonitor();

    static Integer count = 0;

    static class Producer extends Thread {
        Producer(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            do {
                try {
                    LOGGER.info("生产了一条消息：【{}】", count);
                    monitor.put(this.getName(), count++);
                    //Thread.sleep(1000);
                } catch (InterruptedException e) {
                    LOGGER.error("生产消息时产生异常！");
                }
            } while (true);
        }
    }

    static class Consumer extends Thread {
        Consumer(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            do {
                try {
                    Integer item = -1;
                    monitor.get(this.getName(), item);
                    //Thread.sleep(1000);
                } catch (InterruptedException e) {
                    LOGGER.error("消费消息时产生异常！");
                }
            } while (true);
        }
    }

    public static void main(String[] args) {
        Producer p1 = new Producer("producer-1");
        Producer p2 = new Producer("producer-2");
        Producer p3 = new Producer("producer-3");
        Producer p4 = new Producer("producer-4");

        Consumer c1 = new Consumer("consumer-1");
        Consumer c2 = new Consumer("consumer-2");
        Consumer c3 = new Consumer("consumer-3");
        Consumer c4 = new Consumer("consumer-4");

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        c1.start();
        c2.start();
        c3.start();
        c4.start();
    }
}
