package com.github.java.learning.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * created by guanjian on 2021/1/7 14:07
 */
public class RandomAccessFileTest {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\guanjian\\Desktop\\demo.txt";
        RandomAccessFile raf = null;
        File file = null;
        try {
            file = new File(filePath);
            raf = new RandomAccessFile(file, "rw");
            // 获取 RandomAccessFile对象文件指针的位置，初始位置为0
            System.out.print("输入内容：" + raf.getFilePointer());
            //移动文件记录指针的位置
            raf.seek(3);

            raf.write("xxxxx".getBytes());

            int c;
            //循环读取文件
            while ((c = raf.read()) != -1) {
                //输出文件读取的内容
                System.out.println((char)c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            raf.close();
        }

    }
}
