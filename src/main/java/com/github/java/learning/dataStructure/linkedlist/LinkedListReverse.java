package com.github.java.learning.dataStructure.linkedlist;

/**
 * created by guanjian on 2021/3/12 9:37
 */
public class LinkedListReverse {

    public static void reverse(ListNode head) {
        // STEP-1
        // head <- 1 -> 2 -> 3
        //         ^

        // STEP-2
        // head <- 1 <- 2 -> 3
        //              ^

        // STEP-3
        // head <- 1 <- 2 <- 3
        //                   ^

        if (null == head && null == head.next) {
            return;
        }

        while (head != null) {

        }
    }

    /**
     * 打印
     */
    public static void print(ListNode head) {
        while (head != null) {
            System.out.format(" %s -> ", head.data);
            head = head.next;
        }
    }

    public static ListNode buildLinkedList() {
      return null;
    }

    public static void main(String[] args) {
        print(buildLinkedList());
    }

}
