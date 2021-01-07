package com.github.java.learning.io;

import java.io.BufferedReader;
import java.io.StringReader;

/**
 * created by guanjian on 2021/1/7 11:20
 */
public class BufferedReaderTest {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new StringReader("你好"));

        br.lines().forEach(x->{
            System.out.println(x);
        });
    }
}
