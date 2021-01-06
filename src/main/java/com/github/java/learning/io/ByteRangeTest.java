package com.github.java.learning.io;

import java.util.stream.IntStream;

/**
 * Byte范围
 * created by guanjian on 2021/1/6 15:54
 */
public class ByteRangeTest {

    public static void main(String[] args) {

        IntStream.rangeClosed(Byte.MIN_VALUE, Byte.MAX_VALUE).forEach(x -> {
            System.out.format("byte=%s | char=%s \n", x, (char) x);
        });
    }
}
