package com.example.findKthElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author wangxueqing
 * 求第K小问题--通过max heap来解决，堆用PriorityQueue实现大堆，当size > k 时，
 * 将最大堆顶部元素 poll()出来，最大堆始终维护k个最大元素，直到所有元素都枚举完，
 * 最后堆里面的维护的就是最小的k个元素，此时poll出的便是第k小元素
 */
public class FindKthElement {

  public static void main(String[] args) {

  }

  public static List<Integer> solution(List<Integer> nums, int k){
    List<Integer> ans = new ArrayList<>();
    if(nums==null || nums.size() == 0) {
      return ans;
    }

    PriorityQueue<Integer> queue = new PriorityQueue<>(10, (o1, o2) -> o2 - o1);
    Map<Integer,Boolean> done = new HashMap<>();
    for(Integer num : nums){
      if(!done.containsKey(num)) {
        done.put(num, true);
        queue.add(num);
      }
      if(queue.size() > k){
        queue.poll();
      }
    }

    while(!queue.isEmpty()){
      ans.add(queue.poll());
    }

    return ans;
  }
}
