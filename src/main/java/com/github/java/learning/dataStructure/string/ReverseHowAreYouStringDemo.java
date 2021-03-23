package com.github.java.learning.dataStructure.string;

import java.util.Arrays;

public class ReverseHowAreYouStringDemo {

    public static void reverse(String[] arr) {
        if (null == arr || 0 == arr.length) return;

        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        System.out.println(Arrays.toString(arr));
    }

    public static void reverse1(String[] arr) {
        if (null == arr || 0 == arr.length) return;

        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        reverse(new String[]{"how", "are", "you"});
    }
}
