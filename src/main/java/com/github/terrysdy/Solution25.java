package com.github.terrysdy;

import java.util.Objects;

/**
 * <a href='https://leetcode.cn/problems/reverse-nodes-in-k-group/description/'>K 个一组翻转链表</a>
 */
public class Solution25 {

    public static ListNode reverseKGroup(ListNode head, int k) {

        ListNode left = head;
        ListNode right = left;

        int windowSize = 1;
        // 每次翻转窗口的前置节点，初始化是个假节点
        ListNode pre = new ListNode();
        ListNode next = new ListNode();
        ListNode res = null;
        while (Objects.nonNull(right)) {
            right = right.next;
            windowSize++;
            if (k == windowSize && Objects.nonNull(right)) {
                // 记录下翻转窗口后节点
                next = right.next;
                // right.next断开，翻转窗口内的节点
                right.next = null;
                ListNode newLeft = reverseListNode(left);
                // 把窗口接上原来的链
                left.next = next; // 原来的left现在已经在最后面了，接上原来的next
                pre.next = newLeft; // 窗口前的pre，接上翻转后的left

                // 第一次翻转后的新left就是新链表的head
                if (Objects.isNull(res)) {
                    res = newLeft;
                }

                // 重置pre、left、right、windowSize
                pre = left;
                left = left.next;
                right = left;
                windowSize = 1;
            }
        }

        if (Objects.isNull(res)) {
            res = head;
        }


        return res;
    }


    private static ListNode reverseListNode(ListNode head) {
        // 空/单链表，直接返回
        if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return head;
        }

        ListNode pre = null;
        ListNode current = head;
        while (Objects.nonNull(current)) {
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }


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

    public static void main(String[] args) {
        ListNode listNode8 = new ListNode(8);
        ListNode listNode7 = new ListNode(7, listNode8);
        ListNode listNode6 = new ListNode(6,listNode7);
        ListNode listNode5 = new ListNode(5, listNode6);
        ListNode listNode4 = new ListNode(4, listNode5);
        ListNode listNode3 = new ListNode(3, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);

        printListNode(listNode1);
        System.out.println("");

        ListNode res = reverseKGroup(listNode1, 3);
        printListNode(res);

    }

    public static void printListNode(ListNode head) {
        while (Objects.nonNull(head)) {
            System.out.print(head.val + ",");
            head = head.next;
        }
    }
}
