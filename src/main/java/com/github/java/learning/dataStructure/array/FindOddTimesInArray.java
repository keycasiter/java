package com.github.java.learning.dataStructure.array;

import java.util.HashSet;
import java.util.Set;

public class FindOddTimesInArray {

    /**
     * 时间复杂度O(N)
     * 空间复杂度O(N)
     */
    public static Set findOddTimesBySet(int[] arr) {
        if (null == arr || 0 == arr.length) return null;

        Set set = new HashSet();

        for (int i = 0; i < arr.length; i++) {
            if (set.contains(arr[i])) {
                set.remove(arr[i]);
            } else {
                set.add(arr[i]);
            }
        }
        return set;
    }

    /**
     * 时间复杂度 O(N)
     * 空间复杂度 O(1)
     */
    public static int findOddTimesByXOR(int[] arr) {
        if (null == arr || 0 == arr.length) return -1;

        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res ^= arr[i];
        }
        //暂存，可能是
        int tempRes = res;

        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 6, 6, 7, 5, 2, 2};
        System.out.println(findOddTimesBySet(arr));
        System.out.println(findOddTimesByXOR(arr));
    }
}
