package com.github.terrysdy;

import com.alibaba.fastjson.JSONObject;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * <p>
 * 返回 s 所有可能的分割方案。
 * <p>
 * 示例:
 * <p>
 * 输入: "aab"
 * 输出:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author pojun
 */
public class Solution131 {

    static class Solution {

        public List<List<String>> partition(String s) {

            return dfsSolution(s);
        }

        public List<List<String>> dfsSolution(String s) {
            List<List<String>> res = new LinkedList<>();
            dfs("", s, new LinkedList<>(), res);
            return res;
        }

        /**
         * @param currentString 当前候选子串
         * @param remainString 后续可选子串
         * @param currentSolution 当前方案子串列表
         */
        private void dfs(String currentString, String remainString, List<String> currentSolution,
                List<List<String>> res) {
            // 已经遍历完，尝试加入方案列表
            if (null == remainString || "".equals(remainString)) {
                if (palindromeString(currentString)) {
                    currentSolution.add(currentString);
                    res.add(currentSolution);
                }
                return;
            }

            // 当前字符串为回文串，可将当前回文串放入当前方案
            if (palindromeString(currentString)) {
                List<String> nextSolution = new LinkedList<>(currentSolution);
                nextSolution.add(currentString);
                dfs("", remainString, nextSolution, res);
            }

            // 尝试用下一个字符拼接回文串
            Character appendChar = remainString.charAt(0);
            dfs(currentString + appendChar, remainString.substring(1), currentSolution, res);
        }

        private boolean palindromeString(String s) {
            if (null == s || "".equals(s)) {
                return false;
            }
            String reverse = new StringBuilder(s).reverse().toString();
            return reverse.equals(s);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        // System.out.println(JSONObject.toJSONString(solution.partition("aab")));
        System.out.println(JSONObject.toJSONString(solution.partition("abba")));
        // System.out.println(JSONObject.toJSONString(solution.partition("aaaaaa")));
    }
}
