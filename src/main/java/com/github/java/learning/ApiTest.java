package com.github.java.learning;

import java.util.stream.IntStream;

/**
 * created by guanjian on 2020/11/4 17:07
 */
public class ApiTest {

    //测试2的幂次方-1数据二进制展示
    public static void test01() {
        IntStream.rangeClosed(0, 30).forEach(x -> {
            int num = new Double(Math.pow(2, x)).intValue() - 1;
            System.out.format("\t 2的%s次方 num:%s \t binary:%s \n",x, num, Long.toBinaryString(num));
        });
    }


    public static void main(String[] args) {
        String binary = "111111111111111111111111111111111000011100100010000110010001110";

        System.out.println(Long.toBinaryString(Long.MAX_VALUE));
    }
}
