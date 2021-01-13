package com.github.java.learning.nio;

import java.nio.ByteBuffer;

/**
 * created by guanjian on 2021/1/11 9:53
 */
public class ByteBufferTest {
    public static void main(String[] args) {
        byteBufferTest();
    }

    public static void byteBufferTest() {

        // 写入消息体
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.putInt(0xabef0101);
        byteBuffer.putInt(1024); // 今天过节
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 0);

        // 读取消息头，因为写完后position已经到10了，所以需要先反转为0，再从头读取
        byteBuffer.flip();
        printDelimiter(byteBuffer);

        // 读取length
        printLength(byteBuffer);

        // 继续读取剩下数据
        byteBuffer.get();
        byteBuffer.get();
        printByteBuffer(byteBuffer);

        // 我再反转一下，我还可以从头开始读
        byteBuffer.flip();
        printDelimiter(byteBuffer);

        // clear清空一下，再从头开始读
        byteBuffer.clear();
        printDelimiter(byteBuffer);

        // rewind重绕一下
        byteBuffer.rewind();
        printDelimiter(byteBuffer);

        // mark标记一下
        byteBuffer.mark();

        // 再读取length
        printLength(byteBuffer);

        // reset重置，回到读取delimiter的地方
        byteBuffer.reset();
        printByteBuffer(byteBuffer);
    }

    private static void printDelimiter(ByteBuffer buf) {
        int newDelimiter = buf.getInt();
        System.out.printf("delimeter: %s\n", Integer.toHexString(newDelimiter));
        printByteBuffer(buf);
    }

    private static void printLength(ByteBuffer buf) {
        int length = buf.getInt();
        System.out.printf("length: %d\n", length);
        printByteBuffer(buf);
    }

    private static void printByteBuffer(ByteBuffer buf) {
        System.out.printf("position: %d, limit: %d, capacity: %d\n", buf.position(), buf.limit(), buf.capacity());
    }
}
