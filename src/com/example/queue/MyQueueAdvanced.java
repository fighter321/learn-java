package com.example.queue;

import com.sun.tools.javac.util.List;

/**
 * @author wangxueqing
 */
public class MyQueueAdvanced extends MyQueue {

  public static void main(String[] args) {
    MyQueue obj = new MyQueue();
    obj.push(10);
    int param_2 = obj.pop();
    int param_3 = obj.peek();
    boolean param_4 = obj.empty();
    System.out.println(List.of(param_2, param_3, param_4));
  }

  public MyQueueAdvanced() {
    super();
  }

  @Override
  public void push(int x) {
    if(s1.isEmpty()){
      front = x;
    }
    s1.push(x);
  }

  @Override
  public int pop() {
    if(s2.isEmpty()){
      while(!s1.isEmpty()){
        s2.push(s1.pop());
      }
    }
    return s2.pop();
  }

  @Override
  public int peek() {
    if(!s2.isEmpty()){
      return s2.peek();
    }
    return front;
  }

  @Override
  public boolean empty() {
    return s1.isEmpty() && s2.isEmpty();
  }
}
