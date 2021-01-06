package com.github.java.learning.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * created by guanjian on 2021/1/6 18:09
 */
public class FileStreamTest {

    public static void main(String[] args) throws IOException {
        FileInputStream ins = new FileInputStream(new File("C:\\Users\\guanjian\\Desktop\\demo.txt"));

        int c = 0;
        while ((c = ins.read()) != -1) {
            System.out.print((char) c);
        }

        ins.close();
    }
}
