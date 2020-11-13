package com.github.java.learning.monitor.simple;

/**
 * created by guanjian on 2020/11/13 17:32
 */
public class BlockingQueueTest {

    public static void main(String[] args) {
        BlockingQueue blockingQueue = new BlockingQueue();

        ProducerThread p1 = new ProducerThread("producer-1", blockingQueue);
        ProducerThread p2 = new ProducerThread("producer-2", blockingQueue);
        ProducerThread p3 = new ProducerThread("producer-3", blockingQueue);
        ProducerThread p4 = new ProducerThread("producer-4", blockingQueue);
        ProducerThread p5 = new ProducerThread("producer-5", blockingQueue);

        ConsumerThread c1 = new ConsumerThread("consumer-1", blockingQueue);
        ConsumerThread c2 = new ConsumerThread("consumer-2", blockingQueue);
        ConsumerThread c3 = new ConsumerThread("consumer-3", blockingQueue);
        ConsumerThread c4 = new ConsumerThread("consumer-4", blockingQueue);
        ConsumerThread c5 = new ConsumerThread("consumer-5", blockingQueue);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();

        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
    }
}
