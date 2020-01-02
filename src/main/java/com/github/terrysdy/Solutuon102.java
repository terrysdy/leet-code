package com.github.terrysdy;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author pojun
 */
public class Solutuon102 {

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

        private static List<List<Integer>> res;

        /**
         * 带 level 的中序遍历
         */
        public List<List<Integer>> levelOrder(TreeNode root) {
            res = new LinkedList<>();
            inTravel(root, 0);
            return res;
        }

        private void inTravel(TreeNode node, int level) {
            if (null == node) {
                return;
            }
            // 添加根节点
            List<Integer> levelList;
            if (res.isEmpty() || res.size() <= level) {
                levelList = new LinkedList<>();
                res.add(levelList);
            } else {
                levelList = res.get(level);
            }
            levelList.add(node.val);

            // 左树
            inTravel(node.left, level + 1);

            // 右树
            inTravel(node.right, level + 1);
        }
    }

    public static void main(String[] args) {
        Solution.TreeNode node = new Solution.TreeNode(3, new Solution.TreeNode(9),
                new Solution.TreeNode(20, new Solution.TreeNode(15), new Solution.TreeNode(7)));
        Solution solution = new Solution();
        List<List<Integer>> res = solution.levelOrder(node);
        System.out.println(res);
    }
}
