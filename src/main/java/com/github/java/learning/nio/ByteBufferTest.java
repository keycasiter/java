package com.github.java.learning.nio;

import java.nio.ByteBuffer;

/**
 * created by guanjian on 2021/1/11 9:53
 */
public class ByteBufferTest {
    public static void main(String[] args) throws InterruptedException {
        ByteBuffer bb = ByteBuffer.allocateDirect(10);

        Thread.sleep(500000);
    }
}
