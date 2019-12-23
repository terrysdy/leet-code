package com.github.terrysdy;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author pojun
 */
public class Solution105 {

    static class Solution {

        public class TreeNode {

            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int x) {
                val = x;
            }
        }

        /**
         * 前序遍历第一个节点是根，以根节点为界限划分左右树，递归求得 TreeNode
         */
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (null == preorder || 0 == preorder.length) {
                return null;
            }
            int rootVal = preorder[0];
            TreeNode res = new TreeNode(rootVal);
            if (1 == preorder.length) {
                return res;
            }

            // 这一步可以用 hashMap 优化
            int rootInOrderIdx = 0;
            for (int i = 0; i < inorder.length; i++) {
                if (rootVal == inorder[i]) {
                    rootInOrderIdx = i;
                    break;
                }
            }

            // 设置左树
            int[] leftTreePreOrder = new int[rootInOrderIdx];
            int[] leftTreeInOrder = new int[rootInOrderIdx];
            for (int i = 0; i < rootInOrderIdx; i++) {
                leftTreePreOrder[i] = preorder[i + 1];
                leftTreeInOrder[i] = inorder[i];
            }
            res.left = buildTree(leftTreePreOrder, leftTreeInOrder);

            // 设置右树
            int length = preorder.length;
            int[] rightTreePreOrder = new int[length - rootInOrderIdx - 1];
            int[] rightTreeInOrder = new int[length - rootInOrderIdx - 1];
            for (int i = 0; i < length - rootInOrderIdx - 1; i++) {
                rightTreePreOrder[i] = preorder[i + rootInOrderIdx + 1];
                rightTreeInOrder[i] = inorder[i + rootInOrderIdx + 1];
            }
            res.right = buildTree(rightTreePreOrder, rightTreeInOrder);
            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] pre = { 3, 9, 20, 15, 7 };
        int[] in = { 9, 3, 15, 20, 7 };
        Solution.TreeNode res = solution.buildTree(pre, in);

        int[] pre1 = { 1, 2, 4, 5, 3, 6, 7 };
        int[] in1 = { 4, 2, 5, 1, 6, 3, 7 };
        res = solution.buildTree(pre1, in1);

        int[] pre2 = {};
        int[] in2 = {};
        res = solution.buildTree(pre2, in2);

        System.out.println("ok");
    }
}
