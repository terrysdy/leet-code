package com.github.terrysdy;

import com.alibaba.fastjson.JSONObject;

/**
 * https://leetcode-cn.com/problems/game-of-life/
 * <p>
 * 根据百度百科，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在1970年发明的细胞自动机。
 * <p>
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞具有一个初始状态 live（1）即为活细胞， 或
 * dead（0）即为死细胞。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * <p>
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 根据当前状态，写一个函数来计算面板上细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * 输出:
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 * 进阶:
 * <p>
 * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
 * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
 * <p>
 *
 * @author pojun
 */
public class Solution289 {

    static class Solution {

        private static final int TEMP_ALIVE = 2;
        private static final int TEMP_DEAD = -1;

        // 附近八个格子的方向
        private static final int[][] DIRECTION = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 },
                { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

        /**
         * 要同时原地更新，就要让其他格子认得更新过的格子原来是啥状态
         * 规定
         * 0 ==> 1 的，暂时修改成 2
         * 1 ==> 0 的，暂时修改成 -1
         * <p>
         * 第一遍标记修改，第二次遍历修改成最终值
         */
        public void gameOfLife(int[][] board) {
            int length = board.length;
            // 标记
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    boolean becomeAlive = becomeAlive(i, j, board);
                    if (0 == board[i][j] && becomeAlive) {
                        board[i][j] = TEMP_ALIVE;
                    }
                    if (1 == board[i][j] && !becomeAlive) {
                        board[i][j] = TEMP_DEAD;
                    }
                }
            }

            // 赋终值
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (TEMP_DEAD == board[i][j]) {
                        board[i][j] = 0;
                    }
                    if (TEMP_ALIVE == board[i][j]) {
                        board[i][j] = 1;
                    }
                }
            }
        }

        private boolean becomeAlive(int i, int j, int[][] board) {
            int aroundAliveCount = 0;
            for (int[] direction : DIRECTION) {
                int nextI = i + direction[0];
                int nextJ = j + direction[1];
                // 坐标是否合法
                if (nextI >= 0 && nextI < board.length && nextJ >= 0 && nextJ < board[0].length) {
                    if (alive(board[nextI][nextJ])) {
                        aroundAliveCount++;
                    }
                }
            }

            if (0 == board[i][j] && 3 == aroundAliveCount) {
                return true;
            }
            if (1 == board[i][j] && aroundAliveCount >= 2 && aroundAliveCount <= 3) {
                return true;
            }

            return false;
        }

        private boolean alive(int condition) {
            return TEMP_DEAD == condition || 1 == condition;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] board = { { 0, 1, 0 }, { 0, 0, 1 }, { 1, 1, 1, }, { 0, 0, 0 } };
        solution.gameOfLife(board);
        System.out.println(JSONObject.toJSONString(board));
    }
}
