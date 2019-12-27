package com.github.terrysdy;

/**
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author pojun
 */
public class Solution148 {

    static class Solution {

        public static class ListNode {

            int val;
            ListNode next;

            ListNode(int x) {
                val = x;
            }

            public ListNode(int val, ListNode next) {
                this.val = val;
                this.next = next;
            }
        }

        /**
         * 归并排序
         */
        public ListNode sortList(ListNode head) {
            if (null == head) {
                return head;
            }
            return mergeSort(head);
        }

        private ListNode mergeSort(ListNode head) {
            // 链表仅一个元素，直接返回
            if (null == head.next) {
                return head;
            }

            // 快慢指针寻找链表的中间点
            ListNode slow = head;
            ListNode fast = head.next;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            ListNode middle = slow.next;

            // 断开中间连接，分成左右两个链，分别进行排序
            slow.next = null;
            ListNode left = mergeSort(head);
            ListNode right = mergeSort(middle);

            // 合并两个链表
            return merge(left, right);
        }

        private ListNode merge(ListNode left, ListNode right) {
            ListNode newHead;
            if (left.val < right.val) {
                newHead = left;
                left = left.next;
            } else {
                newHead = right;
                right = right.next;
            }

            ListNode temp = newHead;
            while (left != null || right != null) {
                if (right == null || (left != null && left.val < right.val)) {
                    temp.next = left;
                    left = left.next;
                    temp = temp.next;
                } else {
                    temp.next = right;
                    right = right.next;
                    temp = temp.next;
                }
            }
            return newHead;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Solution.ListNode head = new Solution.ListNode(4,
                new Solution.ListNode(2, new Solution.ListNode(1, new Solution.ListNode(3))));
        Solution.ListNode res = solution.sortList(head);
        System.out.println(res);
    }
}
