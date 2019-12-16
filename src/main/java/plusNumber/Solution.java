package plusNumber;

/*
输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。


输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。


 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("aaaa"));
        System.out.println(lengthOfLongestSubstring(""));
    }

    public static int lengthOfLongestSubstring(String s) {
        int length = s.length();
        int sum = 1;
        int front = 0;
        int end = front + 1;
        if(length == 0) {
            sum = 0;
            return sum;
        }

        while(end < length) {
            int tempsum = 0;
            char tempb = s.charAt(end);
            for(int i = front; i < end; i++) {
                char tempa = s.charAt(i);
                if(tempa == tempb) {
                    tempsum = end - front;
                    front = i+1;
                    end = front;
                    sum = Math.max(sum, tempsum);
                    break;
                }
            }
            end++;
        }

        sum = Math.max(sum, (end-front));

        return sum;
    }
}
