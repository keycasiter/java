package com.github.java.learning.io;

import java.io.*;

/**
 * created by guanjian on 2021/1/6 18:18
 */
public class FilterStreamTest {

    public static void main(String[] args) throws IOException {
        /**
         * BufferedInputStream为ByteArrayInputStream提供缓冲
         */
        FilterInputStream fis = new BufferedInputStream(new ByteArrayInputStream("abcdefg".getBytes()));

        int c = 0;
        while ((c = fis.read()) != -1) {
            System.out.print((char) c);
        }
        fis.close();
    }
}
