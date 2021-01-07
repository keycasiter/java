package com.github.java.learning.io;

import java.nio.charset.Charset;
import java.util.stream.IntStream;

/**
 * Byte范围
 * created by guanjian on 2021/1/6 15:54
 */
public class CharRangeTest {

    public static void main(String[] args) {

        IntStream.rangeClosed(Character.MIN_VALUE, Character.MAX_VALUE).forEach(x -> {
            System.out.format("char=%s | char=%s \n", x, (char) x);
        });
    }
}
