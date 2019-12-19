package com.github.terrysdy;

import com.alibaba.fastjson.JSONObject;

/**
 * https://leetcode-cn.com/problems/rotate-image/
 * <p>
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * <p>
 * 将图像顺时针旋转 90 度。
 * <p>
 * 说明：
 * <p>
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * <p>
 * 示例 1:
 * <p>
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * 示例 2:
 * <p>
 * 给定 matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-image
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author pojun
 */
public class Solution48 {

    static class Solution {

        /**
         * [
         * [1,2,3],
         * [4,5,6],
         * [7,8,9]
         * ]
         * 以对角线为轴点，互换位置 2<-->4 3<-->7 ..
         * 得到
         * [1,4,7]
         * [2,5,8]
         * [3,6,9]
         * 再每一行数字交换位置，1<-->7..
         * 得到
         * [7,4,1]
         * [8,5,2]
         * [9,6,3]
         */
        public void rotate(int[][] matrix) {
            if (null == matrix || matrix.length == 0) {
                return;
            }
            /*
             以对角线为轴点，互换位置
              */
            for (int i = 0; i < matrix.length; i++) {
                // 对角线不用换
                int j = i + 1;
                for (; j < matrix.length; j++) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }

            /*
            每一行数字交换
             */
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length / 2; j++) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[i][matrix.length - 1 - j];
                    matrix[i][matrix.length - 1 - j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        Solution solution = new Solution();
        solution.rotate(matrix);
        System.out.println(JSONObject.toJSONString(matrix));
    }
}
