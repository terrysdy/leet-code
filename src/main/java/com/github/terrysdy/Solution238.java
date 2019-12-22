package com.github.terrysdy;

import com.alibaba.fastjson.JSONObject;

/**
 * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author pojun
 */
public class Solution238 {

    static class Solution {

        /**
         * 乘积 = 左积 * 右积，左右遍历一遍即可
         */
        public int[] productExceptSelf(int[] nums) {
            int length = nums.length;
            int[] res = new int[length];

            // 保存左积
            int multipartRes = 1;
            for (int i = 0; i < length; i++) {
                res[i] = multipartRes;
                multipartRes *= nums[i];
            }

            // 乘右积
            multipartRes = 1;
            for (int i = length - 1; i >= 0; i--) {
                res[i] *= multipartRes;
                multipartRes *= nums[i];
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4 };
        System.out.println(JSONObject.toJSONString(new Solution().productExceptSelf(nums)));
    }
}
