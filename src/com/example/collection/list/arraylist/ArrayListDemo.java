package com.example.collection.list.arraylist;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author wangxueqing
 */
public class ArrayListDemo {

  public static void main(String[] args) {
    ArrayList<Integer> nums = new ArrayList<>();

    for (int i = 0; i < 20; i++) {
      nums.add(i);
    }

    Iterator iter = nums.iterator();
    while(iter.hasNext()){
      //nums.remove(iter.next()); // bad operation
      iter.next();
      iter.remove();
    }

    System.out.println(nums);
  }
}
