package com.github.java.learning.dataStructure.array;

public class FindAbsMinInArray {

    public static int findByLoop(int[] arr) {
        if (null == arr || 0 == arr.length) return -1;

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int current = arr[i] > 0 ? arr[i] : -arr[i];

            if (current < min) {
                min = current;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        //升序数组
        int[] arr = new int[]{-10, -5, -2, 7, 15, 50};
        System.out.println(findByLoop(arr));
    }
}
