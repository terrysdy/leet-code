package com.github.terrysdy.weekly_contest_169;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 给你一个方程，左边用 words 表示，右边用 result 表示。
 * <p>
 * 你需要根据以下规则检查方程是否可解：
 * <p>
 * 每个字符都会被解码成一位数字（0 - 9）。
 * 每对不同的字符必须映射到不同的数字。
 * 每个 words[i] 和 result 都会被解码成一个没有前导零的数字。
 * 左侧数字之和（words）等于右侧数字（result）。
 * 如果方程可解，返回 True，否则返回 False。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：words = ["SEND","MORE"], result = "MONEY"
 * 输出：true
 * 解释：映射 'S'-> 9, 'E'->5, 'N'->6, 'D'->7, 'M'->1, 'O'->0, 'R'->8, 'Y'->'2'
 * 所以 "SEND" + "MORE" = "MONEY" ,  9567 + 1085 = 10652
 * 示例 2：
 * <p>
 * 输入：words = ["SIX","SEVEN","SEVEN"], result = "TWENTY"
 * 输出：true
 * 解释：映射 'S'-> 6, 'I'->5, 'X'->0, 'E'->8, 'V'->7, 'N'->2, 'T'->1, 'W'->'3', 'Y'->4
 * 所以 "SIX" + "SEVEN" + "SEVEN" = "TWENTY" ,  650 + 68782 + 68782 = 138214
 * 示例 3：
 * <p>
 * 输入：words = ["THIS","IS","TOO"], result = "FUNNY"
 * 输出：true
 * 示例 4：
 * <p>
 * 输入：words = ["LEET","CODE"], result = "POINT"
 * 输出：false
 *
 * @author pojun
 */
public class Solution5298 {

    static class Solution {

        private static String[] _words;
        private static String _result;

        // 所有字母 list
        private static List<Character> allCharacters;
        // 首字母
        private static Set<Character> headCharacters;

        private static boolean res;

        public boolean isSolvable(String[] words, String result) {
            _words = words;
            _result = result;
            res = false;
            allCharacters = new LinkedList<>();
            headCharacters = new HashSet<>();

            for (String word : words) {
                for (int i = 0; i < word.length(); i++) {
                    char achar = word.charAt(i);
                    if (!allCharacters.contains(achar)) {
                        allCharacters.add(achar);
                    }
                    if (i == 0) {
                        headCharacters.add(achar);
                    }
                }
            }
            for (int i = 0; i < result.length(); i++) {
                char achar = result.charAt(i);
                if (!allCharacters.contains(achar)) {
                    allCharacters.add(achar);
                }
                if (i == 0) {
                    headCharacters.add(achar);
                }
            }

            int[] map = new int[26];
            Arrays.fill(map, -1);

            boolean[] visited = new boolean[10];
            dfs(0, map, visited);

            return res;
        }

        /**
         * dfs 穷举字母与 0-9 的映射
         *
         * @param idx 当前需要分配的字母下标，在 allCharacters 的下标
         * @param currentMap 当前分配给字母的数字，-1 表示没分配
         * @param visited 当前下标的数字是否已被分配
         */
        public void dfs(int idx, int[] currentMap, boolean[] visited) {
            // 已经找到解决方案了直接退出
            if (res) {
                return;
            }

            // 分配完了映射策略，检查是否合法
            if (idx == allCharacters.size()) {
                res = legalSolution(currentMap);
                return;
            }

            if (idx >= allCharacters.size()) {
                return;
            }

            char currentChar = allCharacters.get(idx);
            // 遍历，将字母分配给当前数字
            for (int i = 0; i <= 9; i++) {
                // 数字已经分配给其他字母了，不行
                if (visited[i]) {
                    continue;
                }
                // 首字母不能为 0
                if (i == 0 && headCharacters.contains(currentChar)) {
                    continue;
                }

                // 把 i 分配给 currentChar
                currentMap[currentChar - 'A'] = i;
                visited[i] = true;

                // 分配下个字母
                dfs(idx + 1, currentMap, visited);

                // 换个数字分配的时候，把上个数字分配方案清掉
                visited[i] = false;
            }
        }

        private boolean legalSolution(int[] map) {
            int sum = 0;
            for (String word : _words) {
                int tmp = 0;
                for (char c : word.toCharArray()) {
                    tmp *= 10;
                    tmp += map[c - 'A'];
                }
                sum += tmp;
            }

            int resultValue = 0;
            for (char c : _result.toCharArray()) {
                resultValue *= 10;
                resultValue += map[c - 'A'];
            }

            return sum == resultValue;
        }

        // private void printRes(int[] map) {
        //     Map<Character, Integer> res = new HashMap<>();
        //     for (int i = 0; i < map.length; i++) {
        //         if (map[i] != -1) {
        //             res.put((char) ('A' + i), map[i]);
        //         }
        //     }
        //     System.out.println(JSONObject.toJSONString(res));
        // }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words0 = { "A", "B" };
        String result = "C";
        System.out.println(solution.isSolvable(words0, result));

        String[] words = { "SEND", "MORE" };
        result = "MONEY";
        System.out.println(solution.isSolvable(words, result));

        String[] words1 = { "SIX", "SEVEN", "SEVEN" };
        result = "TWENTY";
        System.out.println(solution.isSolvable(words1, result));

        String[] words2 = { "THIS", "IS", "TOO" };
        result = "FUNNY";
        System.out.println(solution.isSolvable(words2, result));

        long pre = System.currentTimeMillis();
        String[] words3 = { "SIX", "SEVEN", "SEVEN" };
        result = "TWENTY";
        System.out.println(solution.isSolvable(words3, result));
        System.out.println(System.currentTimeMillis() - pre);

        String[] words4 = { "LEET", "CODE" };
        result = "POINT";
        System.out.println(solution.isSolvable(words4, result));
    }
}
