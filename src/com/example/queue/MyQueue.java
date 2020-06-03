package com.example.queue;

import com.sun.tools.javac.util.List;
import java.util.Stack;

/**
 * @author wangxueqing
 */
public class MyQueue {

  protected Stack<Integer> s1, s2;

  protected int front;


  public static void main(String[] args) {
    MyQueue obj = new MyQueue();
    obj.push(10);
    int param_2 = obj.pop();
    int param_3 = obj.peek();
    boolean param_4 = obj.empty();
    System.out.println(List.of(param_2, param_3, param_4));
  }

  public MyQueue() {
    s1 = new Stack<>();
    s2 = new Stack<>();
  }

  public void push(int x){
    if(s1.isEmpty()){
      front = x;
    }
    while(!s1.isEmpty()){
      s2.push(s1.pop());
    }
    s2.push(x);
    while(!s2.isEmpty()){
      s1.push(s2.pop());
    }
  }

  public int pop() {
    int f = s1.pop();
    if(!s1.isEmpty()){
      front = s1.peek();
    }
    return f;
  }

  public int peek() {
    return front;
  }

  public boolean empty() {
    return s1.isEmpty();
  }

}
