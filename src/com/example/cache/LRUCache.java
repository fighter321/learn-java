package com.example.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangxueqing
 */
public class LRUCache {

  private int capacity;

  private int size;

  private Map<Integer, Node> keyToPrev;

  private Node dummy;

  private Node tail;

  class Node {

    int key, value;

    Node next;

    public Node(int k, int v){
      this.key = k;
      this.value = v;
    }
  }

  public static void main(String[] args) {
    LRUCache cache = new LRUCache(2);
    cache.set(2, 1);
    cache.set(1, 1);
    System.out.println(cache.get(2));
    cache.set(4, 1);
    System.out.println(cache.get(1));
    System.out.println(cache.get(2));
  }


  public LRUCache(int capacity) {
    this.capacity = capacity;
    keyToPrev = new HashMap<>();
    dummy = new Node(0, 0);
    tail = dummy;
    size = 0;
  }


  private void set(int key, int value){
    // option 1
    if(get(key) != -1){
      Node prev = keyToPrev.get(key);
      prev.next.value = value;
      return;
    }

    // option 2
    if(size < capacity){
      ++size;
      Node curt = new Node(key, value);
      tail.next = curt;
      keyToPrev.put(key, tail);
      tail = curt;
      return;
    }

    // option 3
    Node first = dummy.next;
    keyToPrev.remove(first.key);
    first.key = key;
    first.value = value;
    keyToPrev.put(key, dummy);
    moveTotail(key);
  }

  private int get(int key) {
    if(!keyToPrev.containsKey(key)){
      return -1;
    }
    moveTotail(key);
    return tail.value;
  }

  private void moveTotail(int key) {
    Node prev = keyToPrev.get(key);
    Node curt = prev.next;

    if(curt == tail) {
      return;
    }

    prev.next = prev.next.next;
    if(prev.next != null){
      keyToPrev.put(prev.next.key, prev);
    }
    tail.next = curt;
    curt.next = null;
    keyToPrev.put(key, tail);
    tail = curt;
  }

}
