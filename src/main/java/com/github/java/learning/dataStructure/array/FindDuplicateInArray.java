package com.github.java.learning.dataStructure.array;

import java.util.HashSet;
import java.util.Set;

/**
 * created by guanjian on 2021/3/12 11:24
 */
public class FindDuplicateInArray {

    /**
     * 集合
     * <p>
     * 空间复杂度 O(N)
     * 时间复杂度 O(N)
     */
    public static int findDuplicateBySet(int[] arr) {
        if (null == arr || 0 == arr.length) return -1;

        Set set = new HashSet();

        for (int i = 0; i < arr.length; i++) {
            if (set.contains(arr[i])) {
                return arr[i];
            } else {
                set.add(arr[i]);
            }
        }
        return -1;
    }

    /**
     * 异或
     * <p>
     * a^0 =a
     * a^a =0
     * <p>
     * 无需额外空间
     * 时间复杂度 O(N)
     */
    public static int findDuplicateXOR(int[] arr) {
        if (null == arr || 0 == arr.length) return -1;

        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result ^= arr[i] ^ i;
        }

        if (0 != result) return result;

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 4, 5, 6, 7, 8};
        System.out.println(findDuplicateXOR(arr));
    }
}
