package com.github.java.learning.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * created by guanjian on 2021/1/7 11:31
 */
public class BufferedStreamTest {

    public static void main(String[] args) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream("abcd".getBytes()));

        int c;
        while ((c = bis.read()) != -1) {
            System.out.println((char)c);
        }
    }
}
