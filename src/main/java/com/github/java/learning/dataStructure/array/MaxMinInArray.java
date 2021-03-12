package com.github.java.learning.dataStructure.array;

public class MaxMinInArray {

    /**
     * 返回最大值 index
     */
    public static int max(int[] arr) {
        if (null == arr || 0 == arr.length) {
            return -1;
        }

        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                index = i;
            }
        }
        return index;
    }

    /**
     * 返回最小值 index
     */
    public static int min(int[] arr) {
        if (null == arr || 0 == arr.length) {
            return -1;
        }

        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,3,4,5,16,1,2,3,2,-32,10};

        System.out.println(max(arr));
        System.out.println(min(arr));
    }
}
