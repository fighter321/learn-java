package com.example.tree;

/**
 * @author wangxueqing
 */
public class DiameterOfBinaryTree {

  /**
   * maxDis 指的是路径所经过的节点数
   */
  private static int maxDis;

  public static void main(String[] args) {
    TreeNode root = new TreeNode(0);
    root.left = new TreeNode(1);
    root.right = new TreeNode(2);
    System.out.println(maxDistance(root));
  }

  private static int maxDistance(TreeNode root) {
    maxDis = 1;
    depth(root);
    return maxDis - 1;
  }

  private static int depth(TreeNode node){
    if (node == null) {
      return 0;
    }
    int L = depth(node.left);
    int R = depth(node.right);

    maxDis = Math.max(maxDis, L + R + 1);
    return Math.max(L, R) + 1;
  }
}
