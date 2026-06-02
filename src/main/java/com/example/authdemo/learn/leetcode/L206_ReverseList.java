package com.example.authdemo.learn.leetcode;


/**
 * 206. 反转链表
 * {@see https://leetcode.cn/problems/reverse-linked-list/description/}
 */
public class L206_ReverseList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode next = null;
        do {
            final ListNode target = head.next;
            head.next = next;
            next = head;
            head = target;
        } while (head != null);
        return next;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode previousListNode = listNode;
        for (int i = 2; i < 6; i++) {
            ListNode v2 = new ListNode(i);
            previousListNode.next = v2;
            previousListNode = v2;
        }
        print(listNode);

        print(reverseList(listNode));
    }

    public static void print(ListNode listNode) {
        while (listNode != null) {
            System.out.printf("%s ", listNode.val);
            listNode = listNode.next;
        }
        System.out.println();
    }
}



