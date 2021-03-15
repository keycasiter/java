package com.github.java.learning.dataStructure.array;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicateInArray {

    public static boolean containsDuplicateBySet(int[] nums) {
        if (null == nums || 0 == nums.length) return false;

        Set<Integer> set = new HashSet();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])){
                return true;
            }else {
                set.add(nums[i]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,3,3,4,3,2,4,2};
        System.out.println(containsDuplicateBySet(nums));
    }
}
