package com.github.java.learning.dataStructure.string;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ReverseStringDemo {

    public static void reverse(char[] arr) {
        if (null == arr || 0 == arr.length) return;

        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        IntStream.range(0,arr.length).forEach(x->{
            System.out.println(arr[x]);
        });
    }

    public static void reverseByBit(char[] arr){
        if (null == arr || 0 == arr.length) return;
        //a^b = c  b = a^c
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            arr[i] = (char) (arr[i] ^ arr[j]);
            arr[j] = (char) (arr[i] ^ arr[j]);
            arr[i] = (char) (arr[i] ^ arr[j]);
        }
        IntStream.range(0,arr.length).forEach(x->{
            System.out.println(arr[x]);
        });
    }

    public static void main(String[] args) {
//        reverse(new char[]{'a','b','c','d','e'});
        reverseByBit(new char[]{'a','b','c','d','e'});
        reverseByBit(new char[]{'a','b','c','d','e'});
    }
}
