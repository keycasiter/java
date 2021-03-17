package com.github.java.learning.dataStructure.math;

/**
 * 如何不使用除法操作符实现两个正整数的除法
 */
public class TwoNumDivideDemo {

    public static void divide(int a, int b) {

        int res = 0;
        int remain = 0;

        while (a > b) {
            a = a - b;
            if (a == 0) {
                remain = 0;
            }
            if (a > 0){
                remain = a;
            }
            res++;
        }
        System.out.format("商=%s，余数=%s", res, remain);
    }

    public static void divide2(int a, int b) {

        int res = 0;
        int remain = 0;

        while (a > b) {
            a = a - b;
            if (a == 0) {
                remain = 0;
            }
            if (a > 0){
                remain = a;
            }
            res++;
        }
        System.out.format("商=%s，余数=%s", res, remain);
    }

    public static void main(String[] args) {
        divide(10,3);
    }
}
