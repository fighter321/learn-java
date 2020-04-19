package com.example.findKthElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author wangxueqing
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
