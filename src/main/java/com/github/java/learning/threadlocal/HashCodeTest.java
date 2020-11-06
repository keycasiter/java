package com.github.java.learning.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author: <a href=mailto:guanjian@jd.com>guanjian23</a>
 * @date: 2020/11/04 19:47
 * @description: 哈希值、索引值生成
 */
public class HashCodeTest {
    private static AtomicInteger nextHashCode =
            new AtomicInteger();

    private static final int HASH_INCREMENT = 0x61c88647;

    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }

    /**
     * 打印哈希值及其生成索引值
     */
    private static void printHashCodeAndIndex() {
        int length = 1 << 4;
        System.out.format("length:%s \n", length);
        IntStream.rangeClosed(0, length).forEach(x -> {
            int hashCode = nextHashCode();
            System.out.format("\t id:%s \t hashCode:%s \t index:%s \n", x, hashCode, hashCode & (length - 1));
        });
    }

    /**
     * 打印哈希值及其二进制
     */
    private static void printHashCodeAndBinary() {
        int length = 1 << 5;
        System.out.format("length:%s \n", length);
        IntStream.range(0, length).forEach(x -> {
            int hashCode = nextHashCode();
            System.out.format("\t id:%s \t hashCode:%s \t binary:%s \n", x, hashCode, Integer.toBinaryString(hashCode));
        });
    }

    public static void main(String[] args) {
        printHashCodeAndIndex();
        printHashCodeAndBinary();
    }
}
