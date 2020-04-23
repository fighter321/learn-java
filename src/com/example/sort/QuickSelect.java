package com.example.sort;


/**
 * @author wangxueqing
 * 求第k大元素 -- 快速选择解法
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
    // key 1 ：这里一定是 left <= right
    while(left <= right){
      while(left <= right && nums[left] < pivot){
        ++left;
      }
      while(left <= right && nums[right] > pivot){
        --right;
      }
      // key 2 : 这个条件很重要，少了这个条件，结果完全错误
      if(left <= right){
        swap(nums, left, right);
        // key 3 : 这里的left，right需要自增，否则会出现死循环
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
