package com.github.terrysdy;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 * 示例 3：
 *
 * 输入：s = "a"
 * 输出："a"
 * 示例 4：
 *
 * 输入：s = "ac"
 * 输出："a"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author shendeyuan
 * @version 1.0
 */
public class Solution5 {

    static class Solution {

        public String longestPalindrome(String s) {
            if (1 == s.length()) {
                return s;
            }

            String res = "";
            for (int i = 0; i < s.length(); i++) {
                if (res.length() >= s.length() - i) {
                    // 已当前 start 为起始已经不可能大于已有的结果，提前结束
                    break;
                }
                char startC = s.charAt(i);
                int endIdx = s.lastIndexOf(startC);

                if (endIdx == i && res.length() < 1) {
                    res = String.valueOf(startC);
                    continue;
                }

                while (endIdx >= i) {
                    if (palindrome(s, i, endIdx)) {
                        String currentResult = s.substring(i, endIdx + 1);
                        if (currentResult.length() > res.length()) {
                            res = currentResult;
                            break;
                        }
                    }
                    // 不是回文就找下一个尾 idx
                    endIdx = s.substring(i, endIdx).lastIndexOf(startC) + i;
                }

            }
            return res;
        }

        /**
         * start-end 是否为回文。两侧逼近判断
         */
        public boolean palindrome(String s, int start, int end) {
            while (start < end) {
                if (s.charAt(start) == s.charAt(end)) {
                    start++;
                    end--;
                    continue;
                }
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.println(solution.longestPalindrome("baeabab"));
        //System.out.println(solution.longestPalindrome("babad"));
        //System.out.println(solution.longestPalindrome("cbbd"));
        //System.out.println(solution.longestPalindrome("ac"));
        //System.out.println(solution.longestPalindrome("aaaaa"));
        System.out.println(solution.longestPalindrome(
            "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"));
    }
}
