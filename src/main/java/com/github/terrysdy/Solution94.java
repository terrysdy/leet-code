package com.github.terrysdy;

import com.alibaba.fastjson.JSONObject;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 * <p>
 * 给定一个二叉树，返回它的中序遍历。
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * 输出: [1,3,2]
 *
 * @author pojun
 */
public class Solution94 {

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

        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new LinkedList<>();
            inOrderTravel(root, res);
            return res;
        }

        public void inOrderTravel(TreeNode current, List<Integer> res) {
            if (null == current) {
                return;
            }
            inOrderTravel(current.left, res);
            res.add(current.val);
            inOrderTravel(current.right, res);
        }
    }

    public static void main(String[] args) {
        Solution.TreeNode treeNode = new Solution.TreeNode(1, null,
                new Solution.TreeNode(2, new Solution.TreeNode(3), null));
        System.out.println(JSONObject.toJSONString(new Solution().inorderTraversal(treeNode)));
    }
}
