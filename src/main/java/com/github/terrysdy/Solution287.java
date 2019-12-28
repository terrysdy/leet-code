package com.github.terrysdy;

/**
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 * <p>
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author pojun
 */
public class Solution287 {

    static class Solution {

        public int findDuplicate(int[] nums) {
            return loopSolution(nums);
        }

        /**
         * 针对候选值的二分
         * 例：数字在 1~10 之间，则中位数是 5，小于 5 的数字个数如果不小于5，则表示候选值在 1~4 之间。一直二分找到候选值
         */
        public int bisectionSolution(int[] nums) {
            int left = 1;
            int right = nums.length - 1;

            while (left < right) {
                int mid = (left + right + 1) / 2;

                // 小于 mid 的数字个数
                int count = 0;
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] < mid) {
                        count++;
                    }
                }

                // 如果个数不小于 mid，则在 left~mid-1 的区间内
                if (count >= mid) {
                    right = mid - 1;
                } else {
                    left++;
                }
            }
            return left;
        }

        /**
         * 把数组当成链表，存在重复元素一定会成环，快慢指针在成环的链表里遍历，一定会相遇
         * 再找到环的入口就是重复元素
         */
        public int loopSolution(int[] nums) {
            // 快慢指针在环中某个位置相遇
            int slow = 0;
            int fast = 0;
            slow = nums[slow];
            fast = nums[nums[fast]];
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }

            // 寻找环的入口？还是没理解为什么这样就是环的入口 todo
            int pre1 = 0;
            int pre2 = slow;
            while (pre1 != pre2) {
                pre1 = nums[pre1];
                pre2 = nums[pre2];
            }
            return pre1;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = { 1, 3, 4, 2, 2 };
        System.out.println(solution.findDuplicate(nums));

        int[] nums1 = { 3, 1, 3, 4, 2 };
        System.out.println(solution.findDuplicate(nums1));

        int[] nums2 = { 1, 1 };
        System.out.println(solution.findDuplicate(nums2));
    }
}
