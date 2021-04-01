package com.github.java.learning.dataStructure;

public class SumDemo {

    public static int sum1(int[] arr) {

        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res += arr[i];
        }
        return res;
    }

    public static int sum2(int[] arr, int size) {
        if (size < 1) return 0;
        return sum2(arr, size - 1) + arr[size - 1];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4};
        System.out.println(sum1(arr));
        System.out.println(sum2(arr, arr.length));
    }
}
