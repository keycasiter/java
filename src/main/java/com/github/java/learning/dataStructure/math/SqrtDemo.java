package com.github.java.learning.dataStructure.math;

/**
 * 设计一个算法，判断给定的一个数n 是否是某个数的二次方，不能使用平方根运算。
 * 例如，16就满足条件,因为它是4的二次方；而15则不满足条件，因为不存在一个数其二次方值为15。
 */
public class SqrtDemo {

    public static boolean isSqrt(int num) {

        if (num <= 0) return false;

        int temp;

        for (int i = 0; i < num; i++) {

            temp = i * i;

            if (temp == num) {
                return true;
            } else if (temp > num) {
                return false;
            }
        }

        return false;
    }

    public static boolean isSqrtByBinarySearch(int num) {
        if (num <= 0) return false;

        int low = 1;
        int high = num;
        int mid;

        while (low < high){
            mid = (low + high)/2;

            if (num < mid * mid){
                high = mid - 1;
            }else if (num > mid * mid){
                low = mid + 1;
            }else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(isSqrt(15));
//        System.out.println(isSqrt(16));
//        System.out.println(isSqrt(133));
//        System.out.println(isSqrt(100));

        System.out.println(isSqrtByBinarySearch(15));
        System.out.println(isSqrtByBinarySearch(16));
        System.out.println(isSqrtByBinarySearch(133));
        System.out.println(isSqrtByBinarySearch(100));
    }
}
