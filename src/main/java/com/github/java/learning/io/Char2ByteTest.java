package com.github.java.learning.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by guanjian on 2021/1/7 10:41
 */
public class Char2ByteTest {

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream("abcd".getBytes()));

        int c = 0;
        while ((c = isr.read()) != -1) {
            System.out.print((char) c);
        }

        isr.close();
    }
}
