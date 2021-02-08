package com.example.datastruct;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * @author wangxueqing
 */
public class SkipList<K extends Comparable<K>, V> implements Iterable<K> {

  protected static final Random randomGenerator = new Random();

  protected static final double DEFAULT_PROBABILITY = 0.5;

  private Node<K, V> head;

  private double probability;

  private int size;

  public static void main(String[] args) {
    SkipList<Integer, String> skipList = new SkipList<>();
    for(int i=0; i<10; ++i){
      skipList.add(i, String.valueOf(i));
    }

    assert skipList.size() == 10;
    assert !skipList.empty();
    assert !skipList.contains(11);
    assert skipList.get(5).equals("5");

    int count = 0;
    for(Integer i : skipList){
      assert skipList.get(i).equals(count++);
    }

    skipList.remove(9);
    assert skipList.size() == 9;
    assert skipList.get(9) == null;
    skipList.remove(8);
    skipList.remove(7);
    skipList.remove(6);
    skipList.remove(5);
    skipList.remove(4);
    skipList.remove(3);
    skipList.remove(2);
    skipList.remove(1);
    skipList.remove(0);
    assert skipList.size() == 0;
    assert skipList.empty();
    System.out.println("success finished skiplist tests");
  }

  public SkipList() {
    this(DEFAULT_PROBABILITY);
  }

  public SkipList(double probability){
    this.probability = probability;
    this.head = new Node<>(null, null, 0);
    this.size = 0;
  }

  public V get(K key){
    checkKeyValidity(key);
    Node<K, V> node = findNode(key);
    if(node.getKey().compareTo(key) == 0){
      return node.getValue();
    }
    return null;
  }

  public void add(K key, V value){
    checkKeyValidity(key);
    Node<K, V> node = findNode(key);
    if(node.getKey() != null && node.getKey().compareTo(key)==0){
      node.setValue(value);
      return;
    }

    Node<K, V> newNode = new Node<>(key, value, node.getLevel());
    horizontalInsert(node, newNode);
    int currentLevel = node.getLevel();
    int headLevel = head.getLevel();
    while(isBuildLevel()){
      // 升级head
      if(currentLevel >= headLevel){
        Node<K, V> newHead = new Node<>(null, null, headLevel+1);
        verticalLink(newHead, head);
        head = newHead;
        headLevel = head.getLevel();
      }

      // 升级node
      while(node.getUp() == null){
        node = node.getPrev();
      }
      node = node.getUp();
      // 链接上一层 node
      Node<K, V> tmp = new Node<>(key, value, node.getLevel());
      horizontalInsert(node, tmp);
      verticalLink(tmp, newNode);
      newNode = tmp;
      currentLevel++;
    }
    size++;
  }

  public void remove(K key){
    checkKeyValidity(key);
    Node<K, V> node = findNode(key);
    if(node.getKey() == null || node.getKey().compareTo(key)!= 0){
      throw new NoSuchElementException("The key is not exist");
    }

    // move to bottom
    while (node.getDown() != null){
      node = node.getDown();
    }

    // remove node by down-top
    Node<K, V> prev, next;
    for(; node != null; node = node.getUp()){
      prev = node.getPrev();
      next = node.getNext();
      if(prev != null){
        prev.setNext(next);
      }
      if(next != null){
        next.setPrev(prev);
      }
    }
    // adjust head
    while(head.getNext() == null && head.getDown() != null){
      head = head.getDown();
      head.setUp(null);
    }
    size--;
  }

  public boolean contains(K key){
    return get(key) != null;
  }

  public boolean empty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  private boolean isBuildLevel(){
    return randomGenerator.nextDouble() < probability;
  }

  private void verticalLink(Node<K, V> x, Node<K, V> y){
    x.setDown(y);
    y.setUp(x);
  }

  private void horizontalInsert(Node<K, V> x, Node<K, V> y){
    y.setNext(x.getNext());
    y.setPrev(x);
    if(x.getNext() != null){
      x.getNext().setPrev(y);
    }
    x.setNext(y);
  }

  protected Node<K, V> findNode(K key){
    checkKeyValidity(key);
    Node<K, V> node = head;
    Node<K, V> next;
    Node<K, V> down;
    K nodeKey;

    while(true){
      next = node.getNext();
      while(next != null && lessThanOrEqual(next.getKey(), key)){
        node = next;
        next = node.getNext();
      }

      nodeKey = node.getKey();
      if(nodeKey != null && nodeKey.compareTo(key) == 0){
        break;
      }
      down = node.getDown();
      if(down != null){
        node = down;
      } else {
        break;
      }
    }
    return node;
  }

  protected boolean lessThanOrEqual(K a, K b) {
    return a.compareTo(b) <= 0;
  }

  private void checkKeyValidity(K key) {
    if(key == null){
      throw new IllegalArgumentException("The key must not be null");
    }
  }

  @Override
  public Iterator<K> iterator() {
    return new SkipListIterator<K, V>(head);
  }


  protected static class SkipListIterator<K extends Comparable<K>, V> implements Iterator<K> {

    private Node<K, V> node;

    public SkipListIterator(Node<K, V> node){
      while(node.getDown() != null){
        node = node.getDown();
      }

      while(node.getPrev() != null){
        node = node.getPrev();
      }

      if(node.getNext() != null){
        node = node.getNext();
      }

      this.node = node;
    }

    @Override
    public boolean hasNext() {
      return this.node != null;
    }

    @Override
    public K next() {
      K result = node.getKey();
      node = node.getNext();
      return result;
    }
  }

  protected static class Node<K extends Comparable<K>, V> {
    private K key;
    private V value;
    private int level;
    private Node up, down, next, prev;

    public Node(K key, V value, int level){
      this.key = key;
      this.value = value;
      this.level = level;
    }

    public K getKey() {
      return key;
    }

    public void setKey(K key) {
      this.key = key;
    }

    public V getValue() {
      return value;
    }

    public void setValue(V value) {
      this.value = value;
    }

    public int getLevel() {
      return level;
    }

    public void setLevel(int level) {
      this.level = level;
    }

    public Node getUp() {
      return up;
    }

    public void setUp(Node up) {
      this.up = up;
    }

    public Node getDown() {
      return down;
    }

    public void setDown(Node down) {
      this.down = down;
    }

    public Node getNext() {
      return next;
    }

    public void setNext(Node next) {
      this.next = next;
    }

    public Node getPrev() {
      return prev;
    }

    public void setPrev(Node prev) {
      this.prev = prev;
    }

    @Override
    public String toString() {
      return "Node{" +
          "key=" + key +
          ", value=" + value +
          ", level=" + level +
          '}';
    }
  }

}
