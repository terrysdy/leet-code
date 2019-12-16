package com.github.terrysdy;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/permutations/
 * <p>
 * 给定一个没有重复数字的序列，返回其所有可能的全排列
 *
 * @author pojun
 */
public class Solution46 {

    static class Solution {

        public List<List<Integer>> permute(int[] nums) {
            return insertSolution(nums);
        }

        /**
         * dfs，每次添加的元素为 dfs 选择
         */
        public List<List<Integer>> dfsSolution(int[] nums) {
            List<Integer> initRemains = new LinkedList<>();
            for (int num : nums) {
                initRemains.add(num);
            }
            List<List<Integer>> res = new LinkedList<>();
            dfs(initRemains, new LinkedList<>(), res);
            return res;
        }

        public void dfs(List<Integer> remainNumbers, List<Integer> selectedNumbers,
                List<List<Integer>> res) {
            if (null == remainNumbers || remainNumbers.isEmpty()) {
                res.add(selectedNumbers);
                return;
            }

            for (int i = 0; i < remainNumbers.size(); i++) {
                List<Integer> nextRemain = new ArrayList<>(remainNumbers);
                nextRemain.remove(i);
                List<Integer> nextSelected = new LinkedList<>(selectedNumbers);
                nextSelected.add(remainNumbers.get(i));
                dfs(nextRemain, nextSelected, res);
            }
        }

        /**
         * 新增一个数字，就在原数字组成的所有全排列中，插入到排列的间隔位置里
         */
        public List<List<Integer>> insertSolution(int[] nums) {
            List<List<Integer>> res = new LinkedList<>();
            for (int num : nums) {
                if (res.isEmpty()) {
                    res.add(new LinkedList<>());
                }

                List<List<Integer>> temp = new LinkedList<>();
                for (List<Integer> existNumList : res) {
                    for (int i = existNumList.size(); i >= 0; i--) {
                        List<Integer> nextList = new LinkedList<>(existNumList);
                        nextList.add(i, num);
                        temp.add(nextList);
                    }
                }
                res = temp;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        Solution solution = new Solution();
        System.out.println(JSONObject.toJSONString(solution.permute(nums)));
        int[] nums1 = { 1 };
        System.out.println(JSONObject.toJSONString(solution.permute(nums1)));
    }
}
