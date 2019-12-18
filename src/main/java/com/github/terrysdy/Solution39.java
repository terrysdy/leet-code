package com.github.terrysdy;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/combination-sum/
 * <p>
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 * <p>
 *
 * @author pojun
 */
public class Solution39 {

    static class Solution {

        private static int _target;
        private static int[] _candidates;
        private static List<List<Integer>> res;

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            return dfsSolution(candidates, target);
        }

        public List<List<Integer>> dfsSolution(int[] candidates, int target) {
            Arrays.sort(candidates);
            _candidates = candidates;
            _target = target;
            res = new LinkedList<>();

            List<Integer> firstList = Collections.emptyList();
            dfs(0, 0, firstList);
            return res;
        }

        /**
         * @param currentSum 当前数字总和
         * @param lastIdx 上一次选择的数字 idx
         * @param currentList 当前 list
         */
        public void dfs(int currentSum, int lastIdx, List<Integer> currentList) {
            // 当前和等于 target，合法解
            if (currentSum == _target) {
                res.add(currentList);
            }

            // 每次只取比不小于上次所选的数字，避免重复
            for (int i = lastIdx; i < _candidates.length; i++) {
                int candidate = _candidates[i];
                if (currentSum + candidate <= _target) {
                    List<Integer> nextList = new LinkedList<>(currentList);
                    nextList.add(candidate);
                    dfs(currentSum + candidate, i, nextList);
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates = { 2, 3, 6, 7 };
        System.out.println(solution.combinationSum(candidates, 7));
        int[] candidates1 = { 2, 3, 5 };
        System.out.println(solution.combinationSum(candidates1, 8));
    }
}
