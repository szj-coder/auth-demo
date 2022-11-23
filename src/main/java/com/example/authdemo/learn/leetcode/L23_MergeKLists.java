package com.example.authdemo.learn.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 23. 合并K个升序链表
 * {@see https://leetcode-cn.com/problems/merge-k-sorted-lists/}
 */
public class L23_MergeKLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        ListNode[] list = lists;
        final ArrayList<ListNode> result = new ArrayList<>();
        do {
            result.clear();
            for (int i = 0; i < list.length; i = i + 2) {
                if (i + 1 >= list.length) {
                    result.add(list[i]);
                } else {
                    result.add(mergeList(list[i], list[i + 1]));
                }
            }
            list = result.toArray(new ListNode[0]);
            System.out.println(Arrays.toString(list));
        } while (result.size() > 1);

        return result.get(0);
    }

    public ListNode mergeList(ListNode listNode1, ListNode listNode2) {
        final ListNode listNode = new ListNode();
        ListNode currentNode = listNode;
        while (listNode1 != null || listNode2 != null) {
            if (listNode1 == null) {
                currentNode.next = new ListNode(listNode2.val);
                currentNode = currentNode.next;
                listNode2 = listNode2.next;
            } else if (listNode2 == null) {
                currentNode.next = new ListNode(listNode1.val);
                currentNode = currentNode.next;
                listNode1 = listNode1.next;
            } else {
                if (listNode1.val < listNode2.val) {
                    currentNode.next = new ListNode(listNode1.val);
                    currentNode = currentNode.next;
                    listNode1 = listNode1.next;
                } else {
                    currentNode.next = new ListNode(listNode2.val);
                    currentNode = currentNode.next;
                    listNode2 = listNode2.next;
                }
            }
        }
        return listNode.next;
    }


    public static void main(String[] args) {
        System.out.println(new L23_MergeKLists().mergeKLists(genListNode(new Integer[][]{{1, 4, 5}, {1, 3, 4}, {2, 6}})));
        System.out.println(new L23_MergeKLists().mergeKLists(genListNode(new Integer[][]{})));
    }

    static ListNode[] genListNode(Integer[][] list) {
        ListNode[] listNode = new ListNode[list.length];
        for (int i = 0; i < list.length; i++) {
            ListNode currentNode = null;
            for (int j = 0; j < list[i].length; j++) {
                if (currentNode == null) {
                    listNode[i] = new ListNode(list[i][j]);
                    currentNode = listNode[i];
                } else {
                    currentNode.next = new ListNode(list[i][j]);
                    currentNode = currentNode.next;
                }
            }
        }
        return listNode;
    }

    static class ListNode {
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
}
