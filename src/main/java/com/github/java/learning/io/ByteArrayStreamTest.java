package com.github.java.learning.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * created by guanjian on 2021/1/6 15:08
 */
public class ByteArrayStreamTest {

    public static void main(String[] args) throws IOException {
        String text = "abcdefg";
        ByteArrayOutputStream bOutput = new ByteArrayOutputStream(text.length());

        bOutput.write(text.getBytes("UTF-8"));

        byte b[] = bOutput.toByteArray();

        for (int x = 0; x < b.length; x++) {
            // 打印字符
            System.out.format((char) b[x] + "\n");
        }

        System.out.println("========转大写=========");
        int c;
        ByteArrayInputStream bInput = new ByteArrayInputStream(b);
        while ((c = bInput.read()) != -1) {
            System.out.println(Character.toUpperCase((char) c));
        }
    }
}
