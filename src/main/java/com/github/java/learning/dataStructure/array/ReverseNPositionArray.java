package com.github.java.learning.dataStructure.array;

import java.util.Arrays;

public class ReverseNPositionArray {

    /**
     * 翻转
     */
    public static void reverse(int[] arr, int start, int end) {
        if (null == arr || 0 == arr.length || start < 0 || end < 0) return;
        System.out.format("start = %s , end = %s", start, end);
        while (start < end) {
            int temp = arr[end];
            arr[end] = arr[start];
            arr[start] = temp;

            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        
        //-1, -100, 3, 99
        //1, 2, 3, 4, 5, 6, 7
        //-1
        int[] nums = new int[]{-1};
        int k = 2;

        int maxIdx = nums.length - 1;

        System.out.println(Arrays.toString(nums));
        // -1, -100, 3, 99  [2,3]
        reverse(nums, maxIdx - k + 1, maxIdx);
        System.out.println(Arrays.toString(nums));
        // -1, -100, 99, 3  [0,1]
        reverse(nums, 0, maxIdx - k);
        System.out.println(Arrays.toString(nums));
        // -100, -1, 99, 3  [0,3]
        reverse(nums, 0, maxIdx);
        // 3, 99, -1, -100
        System.out.println(Arrays.toString(nums));
    }
}
