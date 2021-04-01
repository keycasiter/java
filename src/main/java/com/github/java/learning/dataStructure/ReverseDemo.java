package com.github.java.learning.dataStructure;

import java.util.Arrays;

public class ReverseDemo {

    public static void reverse1(int[] arr, int low, int high) {
        if (low < high) {
            //swap
            int temp = arr[high];
            arr[high] = arr[low];
            arr[low] = temp;
            //recursive
            reverse1(arr, low + 1, high - 1);
        }
    }

    public static void reverse2(int[] arr, int low, int high) {
        while (low < high) {
            //swap
            int temp = arr[high];
            arr[high] = arr[low];
            arr[low] = temp;

            low++;
            high--;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        reverse2(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
