package com.github.java.learning.nio;

import java.nio.ByteBuffer;

/**
 * created by guanjian on 2021/1/11 9:53
 */
public class ByteBufferTest {
    public static void main(String[] args) {

        ByteBuffer bb = ByteBuffer.allocate(10);
        System.out.println(bb);
        bb.put((byte)1);
        System.out.println(bb);
        bb.put((byte)2);
        System.out.println(bb);
        bb.put((byte)3);
        System.out.println(bb);
        bb.put((byte)4);
        System.out.println(bb);
        bb.put((byte)5);
        System.out.println(bb);

        bb.flip();
        System.out.println(bb);
        System.out.println(bb.get());
        System.out.println(bb);
        System.out.println(bb.get());
        System.out.println(bb);
        System.out.println(bb.get());
        System.out.println(bb);
        System.out.println(bb.get());
        System.out.println(bb);
        System.out.println(bb.get());
        System.out.println(bb);
    }
}
