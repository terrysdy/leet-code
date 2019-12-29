package com.github.terrysdy.weekly_contest_169;

import com.alibaba.fastjson.JSONObject;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你 root1 和 root2 这两棵二叉搜索树。
 * <p>
 * 请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root1 = [2,1,4], root2 = [1,0,3]
 * 输出：[0,1,1,2,3,4]
 * 示例 2：
 * <p>
 * 输入：root1 = [0,-10,10], root2 = [5,1,7,0,2]
 * 输出：[-10,0,0,1,2,5,7,10]
 * 示例 3：
 * <p>
 * 输入：root1 = [], root2 = [5,1,7,0,2]
 * 输出：[0,1,2,5,7]
 * 示例 4：
 * <p>
 * 输入：root1 = [0,-10,10], root2 = []
 * 输出：[-10,0,10]
 * 示例 5：
 * <p>
 * <p>
 * <p>
 * 输入：root1 = [1,null,8], root2 = [8,1]
 * 输出：[1,1,8,8]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 每棵树最多有 5000 个节点。
 * 每个节点的值在 [-10^5, 10^5] 之间。
 *
 * @author pojun
 */
public class Solution5296 {

    static class Solution {

        public static class TreeNode {

            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int x) {
                val = x;
            }

            TreeNode(int val, TreeNode left,
                    TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

        public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
            List<Integer> list1 = new LinkedList<>();
            transTreeToList(root1, list1);

            List<Integer> list2 = new LinkedList<>();
            transTreeToList(root2, list2);

            List<Integer> res = new LinkedList<>();
            int idx1 = 0;
            int idx2 = 0;

            while (idx1 < list1.size() || idx2 < list2.size()) {
                if (idx2 >= list2.size() || (idx1 < list1.size() && list1.get(idx1) < list2.get(
                        idx2))) {
                    res.add(list1.get(idx1));
                    idx1++;
                } else {
                    res.add(list2.get(idx2));
                    idx2++;
                }
            }

            return res;
        }

        private void transTreeToList(TreeNode root, List<Integer> res) {
            if (null == root) {
                return;
            }
            if (null != root.left) {
                transTreeToList(root.left, res);
            }
            res.add(root.val);
            if (null != root.right) {
                transTreeToList(root.right, res);
            }
        }
    }

    public static void main(String[] args) {
        Solution.TreeNode root1 = new Solution.TreeNode(0,new Solution.TreeNode(-10),new Solution.TreeNode(10));

        Solution.TreeNode roo2 = null;

        Solution solution = new Solution();

        System.out.println(JSONObject.toJSON(solution.getAllElements(root1, roo2)));
    }
}
