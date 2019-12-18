package com.github.terrysdy;

import com.alibaba.fastjson.JSONObject;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/generate-parentheses/
 * <p>
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * n = 3
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 *
 * @author pojun
 */
public class Solution22 {

    static class Solution {

        class Node {

            private String res;
            private int left; // 剩余可用左节点
            private int right; // 剩余可用右节点

            public Node(String res, int left, int right) {
                this.res = res;
                this.left = left;
                this.right = right;
            }

            public String getRes() {
                return res;
            }

            public int getLeft() {
                return left;
            }

            public int getRight() {
                return right;
            }
        }

        public List<String> generateParenthesis(int n) {
            return dpSolution(n);
        }

        /**
         * 每次在字符串中添加左括号或右括号
         */
        public List<String> dfsSolution(int n) {
            List<String> res = new LinkedList<>();
            dfs(new Node("", n, n), res);
            return res;
        }

        /**
         * @param node 当前节点信息
         * @param res 结果
         */
        public void dfs(Node node, List<String> res) {
            // 左右括号都用完了，合法结果
            if (0 == node.left && 0 == node.right) {
                res.add(node.res);
                return;
            }
            // 用个左括号
            if (node.left > 0) {
                dfs(new Node(node.res + "(", node.left - 1, node.right), res);
            }
            // 用个右括号，右括号不能大于等于左括号
            if (node.right > 0 && node.left < node.right) {
                dfs(new Node(node.res + ")", node.left, node.right - 1), res);
            }
        }

        /**
         * 能用深搜，一般广搜也可以
         */
        public List<String> bfsSolution(int n) {
            List<String> res = new LinkedList<>();
            if (0 == n) {
                return res;
            }
            Queue<Node> queue = new LinkedList<>();
            queue.offer(new Node("", n, n));

            while (!queue.isEmpty()) {
                Node currentNode = queue.poll();
                // 左右括号都用完了，合法结果
                if (0 == currentNode.left && 0 == currentNode.right) {
                    res.add(currentNode.res);
                }
                // 用个左括号
                if (currentNode.left > 0) {
                    queue.offer(new Node(currentNode.res + "(", currentNode.left - 1,
                            currentNode.right));
                }
                // 用个右括号,右括号不能大于等于左括号
                if (currentNode.right > 0 && currentNode.left < currentNode.right) {
                    queue.offer(new Node(currentNode.res + ")", currentNode.left,
                            currentNode.right - 1));
                }
            }

            return res;
        }

        /**
         * 新增一组括号，一定是中间夹着若干个括号组，然后后面跟着若干个括号组
         * 即
         * dp[i] = "(" + dp[j]+")" + dp[i-1-j]。j:0~i-1
         */
        public List<String> dpSolution(int n) {
            List<List<String>> dp = new LinkedList<>();
            dp.add(Collections.singletonList(""));

            for (int i = 1; i <= n; i++) {
                List<String> currentDp = new LinkedList<>();
                for (int j = 0; j < i; j++) {
                    for (String inBracket : dp.get(j)) {
                        for (String nextBracket : dp.get(i - 1 - j)) {
                            currentDp.add("(" + inBracket + ")" + nextBracket);
                        }
                    }
                }
                dp.add(currentDp);
            }
            return dp.get(n);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(JSONObject.toJSONString(solution.generateParenthesis(0)));
        System.out.println(JSONObject.toJSONString(solution.generateParenthesis(3)));
        System.out.println(JSONObject.toJSONString(solution.generateParenthesis(2)));
    }
}
