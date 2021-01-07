package com.github.java.learning.io;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * created by guanjian on 2021/1/7 10:58
 */
public class CharArrayReaderTest {
    public static void main(String[] args) throws IOException {
        CharArrayReader car = new CharArrayReader("你好".toCharArray());

        int c = 0;
        while ((c = car.read()) != -1) {
            System.out.print((char) c);
        }

        car.close();

        System.out.println(Character.MIN_VALUE);
    }
}
