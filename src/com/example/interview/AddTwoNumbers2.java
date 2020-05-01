package com.example.interview;

import java.util.Stack;

/**
 * @author wangxueqing
 *
 * 求两个链表的和，和仍然是个链表
 * eg:
 *  输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 *  输出：7 -> 8 -> 0 -> 7
 *  follow up: 不能修改链表，即不能反转链表
 */
public class AddTwoNumbers2 {

  public static void main(String[] args) {
    ListNode dummy1 = new ListNode(0);
    ListNode head1 = dummy1;
    head1.next = new ListNode(7);
    head1 = head1.next;
    head1.next = new ListNode(2);
    head1 = head1.next;
    head1.next = new ListNode(4);
    head1 = head1.next;
    head1.next = new ListNode(3);

    ListNode dummy2 = new ListNode(0);
    ListNode head2 = dummy2;
    head2.next = new ListNode(5);
    head2 = head2.next;
    head2.next = new ListNode(6);
    head2 = head2.next;
    head2.next = new ListNode(4);

    ListNode head = addTwoNumberII(dummy1.next, dummy2.next);
    while(head != null){
      System.out.printf(String.valueOf(head.val) + "->");
      head = head.next;
    }

  }

  static ListNode addTwoNumberII(ListNode l1, ListNode l2){
    if(l1 == null) {
      return l2;
    }
    if(l2 == null) {
      return l1;
    }

    Stack<ListNode> stk1 = new Stack<>();
    Stack<ListNode> stk2 = new Stack<>();
    Stack<ListNode> stk = new Stack<>();

    while (l1 != null){
      stk1.push(l1);
      l1 = l1.next;
    }

    while (l2 != null){
      stk2.push(l2);
      l2 = l2.next;
    }

    ListNode dummy = new ListNode(0);
    int carry = 0;
    while (!stk1.isEmpty() || !stk1.isEmpty()){
      int sum = carry;
      if(!stk1.isEmpty()){
        sum += stk1.pop().val;
      }
      if(!stk2.isEmpty()){
        sum += stk2.pop().val;
      }
      carry = sum / 10;
      stk.push(new ListNode(sum % 10));
    }

    ListNode head = dummy;
    while(!stk.isEmpty()){
      head.next = stk.pop();
      head = head.next;
    }
    return dummy.next;
  }

  static ListNode addTwoNumerII1(ListNode l1, ListNode l2){
    if(l1 == null){
      return l2;
    }
    if(l2 == null){
      return l1;
    }

    l1 = reverseList(l1);
    l2 = reverseList(l2);
    return reverseList(addTwoList(l1, l2));
  }

  static ListNode reverseList(ListNode head){
    if(head == null || head.next == null) {
      return head;
    }

    ListNode pre = null;
    while(head != null){
      ListNode tmp = head.next;
      head.next = pre;
      pre = head;
      head = tmp;
    }
    return pre;
  }

  static ListNode addTwoList(ListNode l1, ListNode l2){
    if(l1 == null && l2 == null){
      return null;
    }

    int carry = 0;
    ListNode dummy = new ListNode(0);
    ListNode head = dummy;
    while(l1 != null || l2 != null){
      int sum = carry;
      if(l1 != null){
        sum += l1.val;
        l1 = l1.next;
      }
      if(l2 != null){
        sum += l2.val;
        l2 = l2.next;
      }
      carry = sum / 10;
      head.next = new ListNode(sum % 10);
      head = head.next;
    }
    if(carry > 0){
      head.next = new ListNode(carry);
    }
    return dummy.next;
  }
}



class ListNode {
  int val;
  ListNode next;
  public ListNode(int val){
    this.val = val;
  }
}
