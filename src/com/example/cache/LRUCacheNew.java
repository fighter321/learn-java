package com.example.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangxueqing01
 * @date 2021-01-28 7:36 下午
 */
public class LRUCacheNew {

    private int capacity;

    private DoubleLinkedcache cache;

    private Map<Integer, Node> map;

    public LRUCacheNew(int capacity) {
        this.capacity = capacity;
        cache = new DoubleLinkedcache();
        map = new HashMap<>();
    }
    
    private void makeRecently(int key) {
        Node node = map.get(key);
        cache.remove(node);
        cache.addLast(node);
    }
    
    private void addRecently(int key, int val) {
        Node node = new Node(key, val);
        cache.addLast(node);
        map.put(key, node);
    }
    
    private void deleteKey(int key) {
        Node node = map.get(key);
        cache.remove(node);
        map.remove(key);
    }
    
    private void removeLeastRecently() {
        Node node = cache.removeFirst();
        map.remove(node.key);
    }

    public Integer get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        makeRecently(key);
        return map.get(key).val;
    }

    public void put(int key, int val) {
        if (map.containsKey(key)) {
            deleteKey(key);
            addRecently(key, val);
            return;
        }
        if (capacity <= cache.size) {
            removeLeastRecently();
        }
        addRecently(key, val);
    }

    static class DoubleLinkedcache {
        Node head, tail;
        int size;
        public DoubleLinkedcache() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            size = 0;
            head.next = tail;
            tail.prev = head;
        }

        public void addLast(Node node) {
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
            size++;
        }
        
        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
        
        public Node removeFirst() {
            if (head.next == tail) {
                return null;
            }
            Node first = head.next;
            remove(first);
            return first;
        }
    }

    static class Node {
        Node prev, next;
        int key, val;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
