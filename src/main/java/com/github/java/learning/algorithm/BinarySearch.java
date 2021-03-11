package com.github.java.learning.algorithm;

/**
 * created by guanjian on 2021/3/2 15:34
 */
public class BinarySearch {

    public static int search(int[] nums, int target) {
        if (null == nums || 0 == nums.length) return -1;

        int left = 0;
        int right = nums.length -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (target == nums[mid]) {
                return mid;
            }else if (target < nums[mid]) {
                right = mid - 1;
            }else if (target > nums[mid]) {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        int target = 2;

        System.out.println(search(nums, target));
    }
}
