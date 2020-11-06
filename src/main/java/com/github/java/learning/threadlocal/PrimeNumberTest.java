package com.github.java.learning.threadlocal;

import java.util.stream.IntStream;

/**
 * @author: <a href=mailto:guanjian@jd.com>guanjian23</a>
 * @date: 2020/11/04 20:00
 * @description: 质数
 */
public class PrimeNumberTest {

    private static void prime(int n) {
        boolean flag = true;

        for (int i = 2; i <= n / 2; i++) { // you can also use i <= Math.sqrt(num)
            if (n % i == 0){
                flag = false;
            }
        }

        if (flag){
            System.out.println(n);
        }

    }

    public static void main(String[] args) {
        IntStream.rangeClosed(0,100).forEach(x->{
            prime(x);
        });
    }
}
