package com.github.java.learning.dataStructure.array;

public class FindMissingNumInUnOrderArray {

    /**
     * 返回缺少值
     */
    public static int findByXOR(int[] arr) {
        if (null == arr || 0 == arr.length) return -1;

        int result = 0;
        for (int i = 0; i < arr.length + 1; i++) {
            if (i < arr.length) {
                result ^= arr[i];
            }
            result ^= i + 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 6, 5, 1, 7};
        System.out.println(findByXOR(arr));
    }
}
