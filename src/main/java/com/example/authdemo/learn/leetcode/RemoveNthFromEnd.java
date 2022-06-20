package com.example.authdemo.learn.leetcode;

/**
 * 19. 删除链表的倒数第 N 个结点
 * {@see https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/}
 */
public class RemoveNthFromEnd {

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

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int i, j = i = 0;
        final ListNode head1 = new ListNode();
        head1.next = head;
        ListNode headi, headj = headi = head1;
        while (i++ < n) {
            headi = headi.next;
        }
        while (headi.next != null) {
            headi = headi.next;
            headj = headj.next;
        }
        headj.next = headj.next.next;
        return head1.next;
    }

    public static void main(String[] args) {
        new RemoveNthFromEnd().removeNthFromEnd(new ListNode(1), 1);
    }
}
