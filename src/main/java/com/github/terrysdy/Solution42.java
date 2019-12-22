package com.github.terrysdy;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author pojun
 */
public class Solution42 {

    static class Solution {

        public int trap(int[] height) {
            return twoTravelSolution(height);
        }

        /**
         * 左右遍历一次分别设置左右两边最大值
         */
        public int twoTravelSolution(int[] height) {
            int length = height.length;
            int[] leftMax = new int[length];
            int[] rightMax = new int[length];

            // 左刷设置左边最大值
            int max = 0;
            for (int i = 0; i < length; i++) {
                if (height[i] >= max) {
                    max = height[i];
                }
                leftMax[i] = max;
            }

            // 右刷设置右边最大值
            max = 0;
            for (int i = length - 1; i >= 0; i--) {
                if (height[i] >= max) {
                    max = height[i];
                }
                rightMax[i] = max;
            }

            int res = 0;
            for (int i = 0; i < length; i++) {
                int iWater = Math.min(leftMax[i], rightMax[i]) - height[i];
                if (iWater > 0) {
                    res += iWater;
                }
            }
            return res;
        }

        class Point {

            private int left; // 左边最大高度
            private int right; // 右边最大高度

            public Point(int left, int right) {
                this.left = left;
                this.right = right;
            }
        }

        /**
         * 每个位置能接的雨水为，min(左边最大高度,右边最大高度)-本身高度
         */
        public int forceSolution(int[] height) {
            if (null == height || height.length <= 1) {
                return 0;
            }

            int length = height.length;

            // 计算左右两侧高度
            Point[] points = new Point[length];
            for (int i = 0; i < length; i++) {
                // 计算左侧
                int maxHeight = 0;
                for (int j = i - 1; j >= 0; j--) {
                    if (height[j] >= maxHeight) {
                        maxHeight = height[j];
                    }
                }
                int left = maxHeight;

                // 计算右侧
                maxHeight = 0;
                for (int j = i + 1; j < length; j++) {
                    if (height[j] >= maxHeight) {
                        maxHeight = height[j];
                    }
                }
                int right = maxHeight;

                points[i] = new Point(left, right);
            }

            // 遍历计算结果
            int res = 0;
            for (int i = 0; i < length; i++) {
                int iWater = Math.min(points[i].left, points[i].right) - height[i];
                if (iWater > 0) {
                    res += iWater;
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        System.out.println(solution.trap(height));
        System.out.println(solution.twoTravelSolution(height) == solution.forceSolution(height));
        int[] height1 = { 5, 2, 1, 2, 1, 5 };
        System.out.println(solution.trap(height1));
        System.out.println(solution.twoTravelSolution(height1) == solution.forceSolution(height1));

        int[] height2 = { 3, 1, 2 };
        System.out.println(solution.trap(height2));
        System.out.println(solution.twoTravelSolution(height2) == solution.forceSolution(height2));

        int[] height3 = { 4, 0, 2, 3 };
        System.out.println(solution.trap(height3));
        System.out.println(solution.twoTravelSolution(height3) == solution.forceSolution(height3));
    }
}
