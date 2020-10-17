package com.example.collection.list.linkedlist;

/**
 * @author wangxueqing
 */
public class FindNthFromEndOfList {

  public static void main(String[] args) {
    //  Input: list = 1->2->3->4->5->null， n = 2
    //	Output: 2
    ListNode dummy = new ListNode(0);
    ListNode head = dummy;
    head.next = new ListNode(1);
    head = head.next;
    head.next = new ListNode(2);
    head = head.next;
    head.next = new ListNode(3);
    head = head.next;
    head.next = new ListNode(4);
    head = head.next;
    head.next = new ListNode(5);
    ListNode node = find(dummy.next, 2);
    System.out.println(node);
  }

  public static ListNode find(ListNode head, int n){
    if(head == null || n <= 0) {
      return null;
    }

    ListNode slow = head;
    for (int i = 0; i < n; i++) {
      if(head == null) {
        return null;
      }
      head = head.next;
    }

    while(head != null){
      head = head.next;
      slow = slow.next;
    }

    return slow;
  }
}


