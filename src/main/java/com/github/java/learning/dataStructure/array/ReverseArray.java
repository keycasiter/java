package com.github.java.learning.dataStructure.array;

import java.util.Arrays;

public class ReverseArray {

    /**
     * 旋转数组
     */
    public static int[] reverse(int[] arr) {
        if (null == arr || 0 == arr.length) return null;

        int lowIndex = 0;
        int highIndex = arr.length - 1;

        while (lowIndex < highIndex) {
            int temp = arr[lowIndex];
            arr[lowIndex] = arr[highIndex];
            arr[highIndex] = temp;

            lowIndex++;
            highIndex--;
        }

        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6};
        System.out.println(Arrays.toString(reverse(arr)));
    }
}
