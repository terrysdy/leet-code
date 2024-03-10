package com.github.terrysdy;

import java.util.*;

/**
 * <a href='https://leetcode.cn/problems/lru-cache/description/'>146. LRU缓存机制</a>
 */
public class Solution146 {

    /**
     * 直接基于linkedHashMap
     */
//    class LRUCache extends LinkedHashMap<Integer, Integer>{
//        private int capacity;
//
//        public LRUCache(int capacity) {
//            super(capacity, 0.75F, true);
//            this.capacity = capacity;
//        }
//
//        public int get(int key) {
//            return super.getOrDefault(key, -1);
//        }
//
//        public void put(int key, int value) {
//            super.put(key, value);
//        }
//
//        @Override
//        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
//            return size() > capacity;
//        }
//    }

    /**
     * 自己实现，基于hashMap跟双向链表
     */
    static class LRUCache {

        private int size;
        private int capacity;

        /**
         * 内部hash表
         */
        private Map<Integer, Node> cache;

        /**
         * 双向链表头尾
         */
        private Node head;
        private Node tail;


        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            // 头尾使用空的虚拟节点，虚拟节点一直在头尾
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.pre = head;
            cache = new HashMap<>();
        }

        public int get(int key) {
            Node node = cache.get(key);
            if (Objects.isNull(node)) {
                return -1;
            }
            // node原位置移除，加到head
            remove(node);
            addToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            Node exist = cache.get(key);
            // 如果已经存在，删掉原来的
            if (Objects.nonNull(exist)) {
                remove(exist);
            }

            // 把新节点加到头部
            Node newNode = new Node(key, value);
            addToHead(newNode);

            // size处理
            if (Objects.isNull(exist)) {
                size++;
            }
            // 缓存添加
            cache.put(key, newNode);

            // 如果长度超过阈值，淘汰尾部
            if (size > capacity) {
                Node removedNode = removeTail();
                cache.remove(removedNode.key);
                size--;
            }
        }


        private void addToHead(Node node) {
            node.pre = head;
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
        }

        private void remove(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        private Node removeTail() {
            Node removeNode = tail.pre;
            remove(removeNode);
            return removeNode;
        }

        class Node {
            private int key;
            private int value;
            private Node pre;
            private Node next;


            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }

            public Node() {
            }
        }
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(2, 1);
        lRUCache.put(1, 1);
        lRUCache.put(2, 3);
        lRUCache.put(4, 1);
        System.out.println(lRUCache.get(1));
        System.out.println(lRUCache.get(2));
    }

}
