package com.github.test;

import com.github.java.learning.spring.async.AsyncPublisher;
import com.github.java.learning.spring.async.EventObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author: <a href=mailto:guanjian@jd.com>guanjian23</a>
 * @date: 2021/04/06 17:48
 * @description:
 * @copyright www.jd.com
 */
public class SpringAsyncTest extends BaseTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(SpringAsyncTest.class);

    @Resource
    private AsyncPublisher publisher;

    @Test
    public void publish() {
        publisher.publish(new EventObject("test"));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}