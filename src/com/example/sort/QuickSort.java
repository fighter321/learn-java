package com.example.sort;

/**
 * @author wangxueqing
 */
public class QuickSort {

  public static void main(String[] args) {
    int[] nums= new int[]{2,3,1,5,4};
    quickSort(nums, 0, nums.length-1);
    for (int i = 0; i < nums.length; i++) {
      System.out.println(nums[i]);
    }
  }

  private static void quickSort(int[] nums, int start, int end){
    if(start >= end) {
      return;
    }

    int left = start;
    int right = end;
    int pivot = nums[(left+right)/2];
    // key 1：这里的条件一定是 left<=right，因为这样可以将pivot最后放到正确的位置
    while(left <= right){
      while(left<=right && nums[left] < pivot){
        ++left;
      }
      while (left<=right && nums[right] > pivot){
        --right;
      }

      // key 2: 这里的条件一定不能少
      if(left <= right){
        int tmp = nums[right];
        nums[right] = nums[left];
        nums[left] = tmp;
        // key 3: 这里left，right必须都移动一步
        ++left;
        --right;
      }
    }
    // key 4：这里不需要再对pivot重新放置位置
    quickSort(nums, start, right);
    quickSort(nums, left, end);
  }
}