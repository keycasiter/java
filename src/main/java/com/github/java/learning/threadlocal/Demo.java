package com.github.java.learning.threadlocal;

public class Demo {

    public static void main(String[] args) {
        //测试用例-1
        int[] nums = new int[]{1, 2, 3, 4, 5};
        //target 4
        //return 3

        //测试用例-2
        int[] nums2 = new int[]{-2, -2, 0, 1, 2, 3, 4, 4, 4, 4, 4, 4, 5};
        //target -2
        //return 0


//        System.out.println(find(nums1, -2));//4
    }

    public static int find(int[] arr, int target) {
        if (null == arr || arr.length == 0) return -1;

        int left = 0;
        int right = arr.length - 1;

        while (left <= right && left != right) {
            int mid = (right + left) / 2;

            if (target == arr[mid]) {
                for (int i = mid; i > 0; i--) {
                    if (arr[i] != target) {
                        return ++i;
                    }
                }
                return mid;
            }
            if (target > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return -1;
    }
}
