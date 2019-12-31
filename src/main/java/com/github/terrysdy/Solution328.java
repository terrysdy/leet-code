package com.github.terrysdy;

/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <p>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 * <p>
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 * <p>
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/odd-even-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author pojun
 */
public class Solution328 {

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

        public ListNode oddEvenList(ListNode head) {
            if (null == head || null == head.next) {
                return head;
            }
            // 偶数头结点
            ListNode evenHead = head.next;

            // 奇、偶双指针，依次跳跃
            ListNode odd = head;
            ListNode even = head.next;
            while ((odd.next != null && odd.next.next != null) ||
                    (even.next != null && even.next.next != null)) {
                if (odd.next != null && odd.next.next != null) {
                    odd.next = odd.next.next;
                    odd = odd.next;
                }

                if (even.next != null && even.next.next != null) {
                    even.next = even.next.next;
                    even = even.next;
                }
            }
            // 结尾置为 bull
            odd.next = null;
            even.next = null;

            odd.next = evenHead;

            return head;
        }
    }

    public static void main(String[] args) {
        Solution.ListNode listNode = new Solution.ListNode(1,
                new Solution.ListNode(2, new Solution.ListNode(3,
                        new Solution.ListNode(4, new Solution.ListNode(5,
                                new Solution.ListNode(6))))));
        Solution solution = new Solution();
        listNode = solution.oddEvenList(listNode);
        System.out.println(listNode);

        listNode = new Solution.ListNode(1);
        listNode = solution.oddEvenList(listNode);
        System.out.println(listNode);

        listNode = new Solution.ListNode(2,
                new Solution.ListNode(1, new Solution.ListNode(3,
                        new Solution.ListNode(5, new Solution.ListNode(6,
                                new Solution.ListNode(4, new Solution.ListNode(7)))))));
        listNode = solution.oddEvenList(listNode);
        System.out.println(listNode);
    }
}
