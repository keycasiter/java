package com.github.java.learning.io;

import java.io.IOException;
import java.io.StringReader;

/**
 * created by guanjian on 2021/1/7 10:58
 */
public class StringReaderTest {
    public static void main(String[] args) throws IOException {
        StringReader sr = new StringReader("你好");

        int c = 0;
        while ((c = sr.read()) != -1) {
            System.out.print(c);
        }

        sr.close();
    }
}
