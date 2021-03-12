package com.github.java.learning.dataStructure.array;

public class FindTwoObjMinDistance {

    public static int findMinDistance(int[] arr, int a, int b) {
        if (null == arr || 0 == arr.length) return -1;

        int minDistance = Integer.MAX_VALUE;
        int lowLastIdx = -1;
        int highLastIdx = -1;
        int low = a;
        int high = b;

        if (a >= b) {
            high = a;
            low = b;
        }

        for (int cursor = 0; cursor < arr.length; cursor++) {
            if (low == arr[cursor]) {
                lowLastIdx = cursor;
                if (highLastIdx >= 0) {
                    minDistance = Math.min(minDistance, lowLastIdx - highLastIdx);
                }
            }

            if (high == arr[cursor]) {
                highLastIdx = cursor;
                if (lowLastIdx >= 0) {
                    minDistance = Math.min(minDistance, highLastIdx - lowLastIdx);
                }
            }
        }
        return minDistance;
    }

    public static void main(String[] args) {
        //元素可重复
        int[] arr = new int[]{-100, -100, 1, 2, 2, 4, 4, 5, 5};
        int[] arr2 = new int[]{4, 5, 6, 4, 7, 4, 6, 4, 7, 8, 5, 6, 4, 3, 10, 8};
//        System.out.println(findMinDistance(arr, 2, 5));
//        System.out.println(findMinDistance(arr, 0, 10));
//        System.out.println(findMinDistance(arr, -10, 10));
        System.out.println(findMinDistance(arr, -100, 2));
        System.out.println(findMinDistance(arr2, 4, 8));
    }
}
