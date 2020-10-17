package com.example.collection.list.linkedlist;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * @author wangxueqing
 */
public class LinkedListDemo {

  public static void main(String[] args) {
    LinkedList<Integer> nums = new LinkedList<>();
    nums.add(1);
    nums.add(2);
    nums.add(3);
    for(Iterator iter = nums.iterator(); iter.hasNext(); ){
      System.out.println(iter.next());
    }
  }
}
