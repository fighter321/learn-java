package com.example.binarySearch;

/**
 * @author wangxueqing
 */
public class DuplicatedElementArray {

  public static void main(String[] args) {
    int[] nums = new int[]{2,2,2,2,2,3,3,4,4,5,6,7,9};
    System.out.println(find(nums, 2));
  }

  private static int find(int[] nums, int target){
    int left = 0;
    int right = nums.length-1;
    while (left < right){
      int mid = left + (right - left) / 2;
      if(nums[mid] >= target){
        right = mid;
      } else {
        left = mid + 1;
      }
    }

    // right <= left
    if(nums[right] == target){
      return right;
    }
    return -1;
  }
}
