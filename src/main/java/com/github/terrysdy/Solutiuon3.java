package com.github.terrysdy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <a href='https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/'>3. 无重复字符的最长子串</a>
 */
public class Solutiuon3 {


    public static int lengthOfLongestSubstring(String s) {
        if (null == s || s.isEmpty()) {
            return 0;
        }
        if (1 == s.length()) {
            return 1;
        }


        char[] array = s.toCharArray();
        int res = 0;
        int i = 0;
        // 当前字符窗口
        Set<Character> currentSet = new HashSet<>();
        currentSet.add(array[i]);

        for (int j = i + 1; j < s.length(); ) {
            char charJ = array[j];
            // 如果最后一个字符没有重复，右界才可以右移
            if (!currentSet.contains(charJ)) {
                currentSet.add(charJ);
                j++;
            } else {
                // 否则，左界右移
                currentSet.remove(array[i]);
                i++;
            }
            int currentSize = currentSet.size();
            if (currentSize > res) {
                res = currentSize;
            }
        }
        return res;
    }

    public static int lengthOfLongestSubstringV2(String s) {
        if (null == s || s.isEmpty()) {
            return 0;
        }
        if (1 == s.length()) {
            return 1;
        }

        int max = 0;
        int left = 0;
        Map<Character, Integer> charIdxMap = new HashMap<>();
        for (int right = left; right < s.length(); right++) {
            char charRight = s.charAt(right);
            if (charIdxMap.containsKey(charRight)) {
                // 如果右界跟map中某个字符重合
                // 1. 重合字符在左界前面，左界不移动
                // 2. 重合字符在左界后面，左界移动到对应idx+1
                left = Math.max(left, charIdxMap.get(charRight) + 1);
            }
            charIdxMap.put(charRight, right);
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringV2("qrsvbspk"));
    }

}
