package com.github.terrysdy.weekly_contest_169;

import com.alibaba.fastjson.JSONObject;

/**
 * 给你一个整数 n，请你返回 任意 一个由 n 个各不相同的 整数 组成的数组，并且这 n 个数相加和为 0 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 5
 * 输出：[-7,-1,1,3,4]
 * 解释：这些数组也是正确的 [-5,-1,1,2,3]，[-3,-1,2,-2,4]。
 * 示例 2：
 * <p>
 * 输入：n = 3
 * 输出：[-1,0,1]
 * 示例 3：
 * <p>
 * 输入：n = 1
 * 输出：[0]
 *
 * @author pojun
 */
public class Solution5295 {

    static class Solution {

        public int[] sumZero(int n) {
            int[] res = new int[n];
            if (n % 2 == 0) {
                int i = 0;
                while (n > 0) {
                    res[i] = -(i + 1);
                    res[i + 1] = i + 1;
                    n = n - 2;

                    i = i + 2;
                }
            } else {
                res[0] = 0;
                n = n - 1;
                int i = 0;
                while (n > 0) {
                    res[i + 1] = -(i + 1);
                    res[i + 2] = i + 1;
                    n = n - 2;
                    i = i + 2;
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(JSONObject.toJSON(solution.sumZero(1)));
        System.out.println(JSONObject.toJSON(solution.sumZero(4)));
        System.out.println(JSONObject.toJSON(solution.sumZero(5)));
    }
}
