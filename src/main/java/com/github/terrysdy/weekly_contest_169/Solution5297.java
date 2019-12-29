package com.github.terrysdy.weekly_contest_169;

import java.util.HashSet;
import java.util.Set;

/**
 * 这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
 * <p>
 * 请你判断自己是否能够跳到对应元素值为 0 的 任意 下标处。
 * <p>
 * 注意，不管是什么情况下，你都无法跳到数组之外。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [4,2,3,0,3,1,2], start = 5
 * 输出：true
 * 解释：
 * 到达值为 0 的下标 3 有以下可能方案：
 * 下标 5 -> 下标 4 -> 下标 1 -> 下标 3
 * 下标 5 -> 下标 6 -> 下标 4 -> 下标 1 -> 下标 3
 * 示例 2：
 * <p>
 * 输入：arr = [4,2,3,0,3,1,2], start = 0
 * 输出：true
 * 解释：
 * 到达值为 0 的下标 3 有以下可能方案：
 * 下标 0 -> 下标 4 -> 下标 1 -> 下标 3
 * 示例 3：
 * <p>
 * 输入：arr = [3,0,2,1,2], start = 2
 * 输出：false
 * 解释：无法到达值为 0 的下标 1 处。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 5 * 10^4
 * 0 <= arr[i] < arr.length
 * 0 <= start < arr.length
 *
 * @author pojun
 */
public class Solution5297 {

    static class Solution {

        private static int[] array;
        private static int length;
        private static boolean res;

        public boolean canReach(int[] arr, int start) {
            res = false;
            array = arr;
            length = arr.length;
            dfs(start, new HashSet<>());
            return res;
        }

        private void dfs(int idx, Set<Integer> visitedIdx) {
            // 找到了
            int val = array[idx];
            if (val == 0) {
                res = true;
                return;
            }

            // 保存已走过的下标
            Set<Integer> nextVisitedSet = new HashSet<>(visitedIdx.size() + 1);
            nextVisitedSet.addAll(visitedIdx);
            nextVisitedSet.add(idx);

            // 加
            int nextIdx = idx + val;
            if (nextIdx >= 0 && nextIdx < length && !nextVisitedSet.contains(nextIdx)) {
                dfs(nextIdx, nextVisitedSet);
            }

            // 减
            nextIdx = idx - val;
            if (nextIdx >= 0 && nextIdx < length && !nextVisitedSet.contains(nextIdx)) {
                dfs(nextIdx, nextVisitedSet);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] arr = { 4, 2, 3, 0, 3, 1, 2 };
        System.out.println(solution.canReach(arr, 5));
        System.out.println(solution.canReach(arr, 0));

        int[] arr1 = { 3, 0, 2, 1, 2 };
        System.out.println(solution.canReach(arr1, 2));
    }
}
