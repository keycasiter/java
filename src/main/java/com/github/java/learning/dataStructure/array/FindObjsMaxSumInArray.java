package com.github.java.learning.dataStructure.array;

public class FindObjsMaxSumInArray {

    public static int findMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        int sum = 0;

        if (null == arr || 0 == arr.length) return max;

        for (int i = 0; i < arr.length; i++) {

        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, -2, 4, 8, -4, 7, -1, -5};
        System.out.println(findMax(arr));
    }
}
