package com.github.terrysdy;

import java.util.Arrays;

/**
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回
 *
 * @author pojun
 */
public class Solution338 {

    /**
     * dp
     * 从 2^n 开始到 （2^n+1）-1 的数为 0~2^n-1 的高位加一个1
     * <p>
     * ans[i + pow] = ans[i] +1。pow = 2^n 次方
     * <p>
     * ans[0+2] = ans[0]+1
     * ans[1+2] = ans[1]+1
     */
    static class Solution {

        public int[] countBits(int num) {
            int[] res = new int[num + 1];
            res[0] = 0;
            if (num == 0) {
                return res;
            }
            res[1] = 1;

            int pow = 2;
            int i = 0;
            while (pow + i <= num) {
                int idx = i + pow;
                res[idx] = res[i] + 1;
                if (idx == pow * 2 - 1) {
                    pow *= 2;
                    i = 0;
                } else {
                    i++;
                }
            }

            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.countBits(0)));
        System.out.println(Arrays.toString(solution.countBits(1)));
        System.out.println(Arrays.toString(solution.countBits(2)));
        System.out.println(Arrays.toString(solution.countBits(5)));
    }
}
