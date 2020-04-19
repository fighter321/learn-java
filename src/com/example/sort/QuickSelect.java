package com.example.sort;


/**
 * @author wangxueqing
 */
public class QuickSelect {

  public static void main(String[] args) {
    int[] nums = new int[]{2,1,4,3,5};
    int res = quickSelect(nums, 0, nums.length-1, 3);
    System.out.println(res);
  }


  private static int quickSelect(int[] nums, int start, int end, int k){
    if(start >= end){
      return nums[k];
    }
    int left = start;
    int right = end;
    int pivot = nums[(left+right)/2];
    while(left <= right){
      while(left <= right && nums[left] < pivot){
        ++left;
      }
      while(left <= right && nums[right] > pivot){
        --right;
      }

      if(left <= right){
        swap(nums, left, right);
        ++left;
        --right;
      }
    }

    // now left > right, so start <= right < left <= end.
    if(k < right){
      return quickSelect(nums, start, right, k);
    }
    if(k > left){
      return quickSelect(nums, left, end, k);
    }
    return nums[k];
  }

  private static void swap(int[] nums, int i, int j){
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }

}
