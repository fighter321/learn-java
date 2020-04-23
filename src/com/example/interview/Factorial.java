package com.example.interview;

/**
 * @author wangxueqing
 *
 * 查找 n!的末尾的出现连续0的个数
 * 这个题偏数学规律，需要观察出最后阶乘的结果0的个数与n被5整除的次数有关
 *
 * 另一种解法就是动态规划 时间复杂度o(n) 空间复杂度o(n)
 * 这个解法的问题在于阶乘数太大很容易整型溢出
 */
public class Factorial {


  public static void main(String[] args) {
    System.out.println(findZeroNumber(100));
  }

  private static int findZeroNumber(int n){

    int num = 0;
    while(n > 0){
      num += n / 5;
      n /= 5;
    }

    return num;

  }

}
