package com.github.java.learning.dataStructure.array;

import java.util.Arrays;

public class ReverseNPositionArray {

    public static void reverse(int[] arr, int k) {
        if (null == arr || 0 == arr.length || 0 == k) return;

        //最大索引
        int maxIdx = arr.length - 1;

        //临时存储对象
        Integer tempObj = null;
        //临时存储对象索引
        int tempIdx = 0;

        for (int i = 0; i < arr.length; i++) {
            //获取k索引
            int kIdx = getK(tempIdx, k, maxIdx);

            //临时存储对象
            if (tempObj == null) {
                tempObj = arr[kIdx];
                arr[kIdx] = arr[0];
            } else {
                int temp = arr[kIdx];
                //更新kIdx对象
                arr[kIdx] = tempObj;
                tempObj = temp;
            }
            tempIdx = kIdx;
        }
    }

    /**
     * 计算K位索引
     */
    public static int getK(int curIdx, int k, int maxIdx) {
        //计算偏移量K的索引位
        return curIdx + k > maxIdx ? curIdx + k - maxIdx - 1 : curIdx + k;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k = 3;

        reverse(nums, k);

        System.out.println(Arrays.toString(nums));
    }
}
