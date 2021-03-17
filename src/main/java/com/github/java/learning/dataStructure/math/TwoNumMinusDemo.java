package com.github.java.learning.dataStructure.math;

public class TwoNumMinusDemo {

    public static int add(int a, int b) {
        int more = 0;
        int sum = 0;
        do {
            //不考虑进位的二进制计算
            sum = a ^ b;
            //进位情况
            more = (a & b) << 1;

            a = sum;
            b = more;
        } while (more > 0);

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(add(2, add(~0,1)));
        System.out.println(add(131, add(~22,1)));
    }
}
