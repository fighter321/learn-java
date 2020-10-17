package com.example.collection.list.linkedlist;


/**
 * @author wangxueqing
 * 删除链表的倒数第N个节点，并返回新的链表头
 */
public class RemoveNthFromEndOfList {

  public static void main(String[] args) {
    //  Input: list = 1->2->3->4->5->null， n = 2
    //	Output: 1->2->3->5->null
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
    ListNode newHead = remove(dummy.next, 2);
    while(newHead != null){
      System.out.printf(newHead.val + "->");
      newHead = newHead.next;
    }
  }

  public static ListNode remove(ListNode head, int n){
    if(n <= 0 || head == null) {
      return null;
    }

    // key 1: 这个添加的节点非常重要，这样在head指针走N步后，slow跟head同步走，直到head到达终点，
    // 这时，slow所处的位置刚好是倒数第N个节点的prev节点
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode slow = dummy;

    for(int i=0; i<n; ++i){
      if(head == null){
        return null;
      }
      head = head.next;
    }

    while(head != null){
      head = head.next;
      slow = slow.next;
    }
    if(slow.next == null) {
      return null;
    }
    slow.next = slow.next.next;

    return dummy.next;
  }
}

