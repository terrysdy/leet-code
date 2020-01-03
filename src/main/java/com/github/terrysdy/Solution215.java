package com.github.terrysdy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author pojun
 */
public class Solution215 {

    static class Solution {

        public int findKthLargest(int[] nums, int k) {
            return priorityQueueSolution(nums, k);
        }

        /**
         * 利用优先队列构建小顶堆。O(nlgk)
         */
        private int priorityQueueSolution(int[] nums, int k) {
            PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o));
            for (int i = 0; i < nums.length; i++) {
                queue.add(nums[i]);
                if (queue.size() > k) {
                    queue.poll();
                }
            }
            return queue.poll();
        }
    }
}
