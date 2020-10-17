package com.example.collection.list.linkedlist;

class ListNode {
  int val;
  ListNode next;
  public ListNode(int val){
    this.val = val;
  }

  @Override
  public String toString() {
    return new StringBuilder()
        .append("ListNode {")
        .append("int val: ")
        .append(val)
        .append(", next:")
        .append(next)
        .append("}").toString();
  }
}
