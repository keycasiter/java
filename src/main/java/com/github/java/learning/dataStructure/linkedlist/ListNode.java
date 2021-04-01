package com.github.java.learning.dataStructure.linkedlist;

/**
 * created by guanjian on 2021/3/12 9:44
 */
public class ListNode<T> {

    public T data;

    public ListNode next;

    public ListNode pre;

    public ListNode() {
    }

    public ListNode(T data) {
        this.data = data;
    }

    public ListNode(T data, ListNode next) {
        this.data = data;
        this.next = next;
    }
}
