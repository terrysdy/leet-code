package com.github.terrysdy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *  
 *
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 *
 * 输入：s = "(]"
 * 输出：false
 * 示例 4：
 *
 * 输入：s = "([)]"
 * 输出：false
 * 示例 5：
 *
 * 输入：s = "{[]}"
 * 输出：true
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author shendeyuan
 * @version 1.0
 */
public class Solution20 {
    static class Solution {

        private static final Map<Character, Character> RELATION = new HashMap<Character, Character>() {{
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }};

        public boolean isValid(String s) {
            if (null == s || s.length() < 1) {
                return false;
            }
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                Character character = s.charAt(i);
                if (stack.empty()) {
                    stack.push(character);
                    continue;
                }

                // 栈顶的字符是否为当前字符左括号
                Character top = stack.peek();
                if (character.equals(RELATION.get(top))) {
                    stack.pop();
                    continue;
                }

                // 右括号提前 return
                if (!RELATION.containsKey(character)) {
                    return false;
                }

                stack.push(character);
            }
            if (stack.empty()) {
                return true;
            }
            return false;

        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid("()[]{}"));
        System.out.println(solution.isValid("{[]}"));
        System.out.println(solution.isValid("([)]"));
        System.out.println(solution.isValid("("));
        System.out.println(solution.isValid("[[[]]]"));
    }
}
