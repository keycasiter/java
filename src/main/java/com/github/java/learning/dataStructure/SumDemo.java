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

    public static int sum3(int[] arr, int low, int high) {
        if (low == high) return arr[low];

        int mid = (low + high) / 2;
        return sum3(arr, low, mid) + sum3(arr, mid + 1, high);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4};
        System.out.println(sum1(arr));
        System.out.println(sum2(arr, arr.length));
        System.out.println(sum3(arr, 0, arr.length - 1));
    }
}
