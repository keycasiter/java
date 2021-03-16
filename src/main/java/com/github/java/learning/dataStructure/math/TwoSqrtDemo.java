package com.github.java.learning.dataStructure.math;

/**
 * 如何判断一个数是否为2的n次方
 */
public class TwoSqrtDemo {

    public static boolean isTwoSqrt(int num) {
        if (0 > num) return false;

        for (int i = 0; i < 32; i++) {
            if (2 << i == num) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTwoSqrtAnd(int num) {
        if (0 > num) return false;
        if ((num & (num -1)) == 0) return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isTwoSqrt(3));
        System.out.println(isTwoSqrt(4));
        System.out.println(isTwoSqrtAnd(8));
        System.out.println(isTwoSqrtAnd(3));

    }
}
