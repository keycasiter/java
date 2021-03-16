package com.github.java.learning.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * created by guanjian on 2021/3/16 10:51
 */
public class DelayedQueueDemo {

    public class Event implements Delayed {


        @Override
        public long getDelay(TimeUnit unit) {
            return 0;
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }
    }

    public static void main(String[] args) {
        DelayQueue<Event> queue = new DelayQueue();

    }
}
