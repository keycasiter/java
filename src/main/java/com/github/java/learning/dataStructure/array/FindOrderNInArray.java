package com.github.java.learning.dataStructure.array;

import java.util.Stack;

public class FindOrderNInArray {

    /**
     * 排序法查找
     */
    public static int findByOrderBigN(int[] arr, int bigN) {
        if (null == arr || 0 == arr.length) return -1;
        //快排
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 0, 1, 0, 2, 3};

        System.out.println(findByOrderBigN(arr, 1));
    }
}
