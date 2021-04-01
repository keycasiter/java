package com.github.java.learning.dataStructure.linkedlist;

/**
 * 无序列表
 */
public class ListTemplate<T> {

    private ListNode<T> header = new ListNode();

    private ListNode<T> tailer = new ListNode();

    private int size = 0;

    public ListTemplate() {
        this.header.next = tailer;
        this.tailer.pre = header;
    }

    public void addLast(ListNode node) {
        //pre
        tailer.pre.next = node;
        //node
        node.pre = tailer.pre;
        node.next = tailer;
        //tailer
        tailer.pre = node;
        size++;
    }

    public void addFirst(ListNode node) {
        //next
        header.next.pre = node;
        //node
        node.next = header.next;
        node.pre = header;
        //header
        header.next = node;
        size++;
    }

    public boolean contains(ListNode node) {
        ListNode curNode = header.next;
        while (curNode != tailer) {
            if (node == curNode) return true;
            curNode = curNode.next;
        }
        return false;
    }

    public boolean remove(ListNode node) {
        ListNode curNode = header.next;
        while (curNode != tailer) {
            if (node == curNode) {
                //pre
                node.pre.next = node.next;
                //next
                node.next.pre = node;
                //size
                size--;

                return true;
            }
            curNode = curNode.next;
        }
        return false;
    }

    public int size() {
        return this.size;
    }

    public void print() {
        ListNode curNode = header.next;
        while (curNode != tailer) {
            System.out.println(curNode.data);
            curNode = curNode.next;
        }
    }

    public static void main(String[] args) {
        ListTemplate<Integer> list = new ListTemplate();

        ListNode node0 = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);

        list.addLast(node1);
        list.addLast(node2);
        list.addLast(node3);
        list.addLast(node4);
        list.addLast(node5);
        list.addFirst(node0);
        list.print();
        System.out.println("size: " + list.size());
        System.out.println(list.contains(node1));
        System.out.println(list.contains(node6));

        System.out.println(list.remove(node3));
        list.print();

        System.out.println(list.remove(node6));
    }
}
