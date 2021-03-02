package com.github.java.learning.algorithm;

/**
 * created by guanjian on 2021/3/2 15:34
 */
public class BinarySearch {

    public static int search(int[] nums, int target) {
        if (null == nums || 0 == nums.length) return -1;

        int left = 0;
        int right = nums.length;
        int count = 0;

        while (left <= right) {
            int mid = (left + right) >>> 1;

            if (target == nums[mid]) {
                System.out.println("count:" + count);
                return mid;
            }

            if (target < nums[mid]) {
                right = mid;
            }
            if (target > nums[mid]) {
                left = mid;
            }
            count++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12,22,33,44,66};
        int target = 66;

        System.out.println(search(nums, target));
    }
}
