package com.github.java.learning.dataStructure.array;

import java.util.HashSet;
import java.util.Set;

public class FindOneTimesInArray {

    public static Set findBySet(int[] arr) {
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

    public static Set findByXOR(int[] arr) {
        if (null == arr || 0 == arr.length) return null;

        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result ^= arr[i];
        }
        System.out.format("totalResult = %s", result);
        int totalResult = result;

        Set set = new HashSet();

        return set;
    }

    public static void main(String[] args) {
        //{1, 2, 4, 5, 6, 4, 2}
        //{1, 5, 6}
        int[] arr = new int[]{1, 2, 4, 5, 6, 4, 2};
        System.out.println(findBySet(arr));
    }
}
