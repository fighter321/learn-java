package com.example.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author wangxueqing
 */
public class BinaryTree {

  private static List<Integer> result;

  private static Stack<TreeNode> stack;

  public static void main(String[] args) {

  }

  public static List<Integer> preOrderTraversal(TreeNode root) {
    result = new ArrayList<>();
    if(root == null){
      return result;
    }
    preTravel(root, result);
    return result;
  }
  private static void preTravel(TreeNode root, List<Integer> result){
    if(root == null) {
      return;
    }
    result.add(root.val);
    preTravel(root.left, result);
    preTravel(root.right, result);
  }

  public static List<Integer> nonRecurPreOrderTravel(TreeNode root) {
    result = new ArrayList<>();
    stack = new Stack<>();
    if(root == null){
      return result;
    }

    stack.push(root);
    while(!stack.isEmpty()){
      TreeNode node = stack.pop();
      result.add(node.val);
      if(node.right != null){
        stack.push(node.right);
      }
      if(node.left != null){
        stack.push(node.left);
      }
    }
    return result;
  }

  public static List<Integer> inOrderTraversal(TreeNode root) {
    result = new ArrayList<>();
    if(root == null){
      return result;
    }
    inTravel(root, result);
    return result;
  }

  private static void inTravel(TreeNode root, List<Integer> result){
    if(root == null) {
      return;
    }
    inTravel(root.left, result);
    result.add(root.val);
    inTravel(root.right, result);
  }

  public static List<Integer> nonRecurInOrderTravel(TreeNode root) {
    result = new ArrayList<>();
    stack = new Stack<>();
    if(root == null){
      return result;
    }

    stack.push(root);
    TreeNode curt = root;
    while(curt != null || !stack.isEmpty()){
      while(curt != null){
        stack.push(curt.left);
        curt = curt.left;
      }

      curt = stack.pop();
      result.add(curt.val);
      curt = curt.right;
    }

    return result;
  }

  public static List<Integer> postOrderTraversal(TreeNode root) {
    result = new ArrayList<>();
    if(root == null){
      return result;
    }
    postTravel(root, result);
    return result;
  }

  private static void postTravel(TreeNode root, List<Integer> result){
    if(root == null) {
      return;
    }
    postTravel(root.left, result);
    postTravel(root.right, result);
    result.add(root.val);
  }

  public static List<Integer> nonRecurPostOrderTravel(TreeNode root) {
    result = new ArrayList<>();
    stack = new Stack<>();
    if(root == null){
      return result;
    }

    TreeNode pre = null;
    TreeNode curt = null;
    while(!stack.isEmpty()){
      curt = stack.peek();
      // traverse down the tree
      if(pre != null || pre.left == curt || pre.right == curt) {
        if (curt.left != null) {
          stack.push(curt.left);
        }
        if (curt.right != null) {
          stack.push(curt.right);
        }
      } else if(curt.left == pre){
        if(curt.right != null){
          stack.push(curt.right);
        }
      } else {
        result.add(curt.val);
        stack.pop();
      }

      pre = curt;
    }

    return result;
  }
}
