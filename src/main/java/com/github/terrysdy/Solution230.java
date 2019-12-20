package com.github.terrysdy;

/**
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
 * <p>
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 * <p>
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [3,1,4,null,2], k = 1
 * 3
 * / \
 * 1   4
 * \
 *    2
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * 输出: 3
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 *
 * @author pojun
 */
public class Solution230 {

    static class Solution {

        public static class TreeNode {

            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int x) {
                val = x;
            }

            public TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

        private static int res;
        private static int i;
        private static int targetK;

        public int kthSmallest(TreeNode root, int k) {
            res = -1;
            i = 0;
            targetK = k;
            inOrderTravel(root);
            return res;
        }

        public void inOrderTravel(TreeNode root) {
            if (null == root) {
                return;
            }
            inOrderTravel(root.left);

            // 已经找到第 k 小的数字，提前返回
            if (++i == targetK) {
                res = root.val;
                return;
            }
            inOrderTravel(root.right);
        }
    }

    public static void main(String[] args) {
        Solution.TreeNode root = new Solution.TreeNode(5,
                new Solution.TreeNode(3, new Solution.TreeNode(2, new Solution.TreeNode(1), null),
                        new Solution.TreeNode(4)), new Solution.TreeNode(6));
        Solution solution = new Solution();
        System.out.println(solution.kthSmallest(root, 3));
        root = new Solution.TreeNode(5,
                new Solution.TreeNode(3, new Solution.TreeNode(2, new Solution.TreeNode(1), null),
                        new Solution.TreeNode(4)), new Solution.TreeNode(6));
        System.out.println(solution.kthSmallest(root, 2));
    }
}
