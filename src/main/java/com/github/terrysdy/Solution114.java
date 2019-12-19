package com.github.terrysdy;

import com.alibaba.fastjson.JSONObject;

/**
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 * <p>
 * 给定一个二叉树，原地将它展开为链表。
 * <p>
 * 例如，给定二叉树
 * <p>
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * 将其展开为：
 * <p>
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 * <p>
 *
 * @author pojun
 */
public class Solution114 {

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

        public void flatten(TreeNode root) {
            if (null == root) {
                return;
            }
            if (null != root.right) {
                flatten(root.right);
            }
            if (null != root.left) {
                flatten(root.left);

                // 把已经铺平的左树插到原右树前面
                TreeNode preRight = root.right;
                TreeNode leftLeaf = root.left;
                while (null != leftLeaf.right) {
                    leftLeaf = leftLeaf.right;
                }
                leftLeaf.right = preRight;
                root.right = root.left;
                root.left = null;
            }
        }
    }

    public static void main(String[] args) {
        Solution.TreeNode root = new Solution.TreeNode(1,
                new Solution.TreeNode(2, new Solution.TreeNode(3), new Solution.TreeNode(4)), null);
        new Solution().flatten(root);
        System.out.println(JSONObject.toJSONString(root));
    }
}
