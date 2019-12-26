package com.github.terrysdy;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * <p>
 * 示例:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 * <p>
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author pojun
 */
public class Solution208 {

    static class Trie {

        private TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                char achar = word.charAt(i);
                if (current.containsKey(achar)) {
                    current = current.getKey(achar);
                } else {
                    current.put(achar, new TrieNode());
                    current = current.getKey(achar);
                }
            }
            current.setEnd();
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            TrieNode node = searchPrefixWith(word);
            return null != node && node.isEnd();
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            TrieNode node = searchPrefixWith(prefix);
            return null != node;
        }

        private TrieNode searchPrefixWith(String prefix) {
            TrieNode node = root;
            for (int i = 0; i < prefix.length(); i++) {
                char achar = prefix.charAt(i);
                if (node.containsKey(achar)) {
                    node = node.getKey(achar);
                } else {
                    node = null;
                    break;
                }
            }
            return node;
        }
    }

    static class TrieNode {

        private boolean end;
        private TrieNode[] nextChars; // 数组下标表示 a-z
        private static final int num = 26;

        public TrieNode() {
            nextChars = new TrieNode[num];
        }

        public boolean containsKey(char achar) {
            return nextChars[achar - 'a'] != null;
        }

        public TrieNode getKey(char achar) {
            return nextChars[achar - 'a'];
        }

        public void put(char achar, TrieNode node) {
            nextChars[achar - 'a'] = node;
        }

        public void setEnd() {
            end = true;
        }

        public boolean isEnd() {
            return end;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("leet");
        System.out.println(trie.search("leet"));
        System.out.println(trie.startsWith("le"));
        System.out.println(trie.startsWith("lee"));

        System.out.println(trie.startsWith("co"));
        trie.insert("code");
        System.out.println(trie.startsWith("co"));
        System.out.println(trie.search("code"));
    }
}
