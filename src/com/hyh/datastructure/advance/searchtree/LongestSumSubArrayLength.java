package com.hyh.datastructure.advance.searchtree;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个数组arr（有0、正、负），和一个整数num，求在arr中，累加和等于num的最长子数组的长度
 * 例子：
 *   arr = {7,3,2,1,1,7,7,7} num = 7
 *   其中有很多的子数组累加和等于7，但是最长的子数组是{3,2,1,1}，所以返回其长度4
 *   普遍思路：必须以每个位置结尾的情况下，如果求出答案，那答案一定在其中。
 *   以100位结尾，从0~1000sum为2000，如果发现0~17位1200，那么18~1000就是我们要求的以1000结尾最长数组。
 *
 * 操作如下：
 * 准备一个sum每走一步就累加，准备一个map记录第一次出现累加数据的位置，默认加入0,-1因为累加0不需要任何数据参与。
 * 然后每走一步都把sum-aim，然后去map中找，第一次出现sum-aim的值的位置
 * 如果有就是这个位置+1当前位置可以出现aim，记录长度
 * 如果没有就加入到map中（注意只加第一次出现的位置，后面再遇见用样的数都不更新）然后继续下一步...
 *
 *扩展：
 * 一个数组中，只有整数，有奇数偶数，求奇数和偶数个数相等的最长子数组。
 * 做法：把奇数改为1，偶数改为-1，把累加和为0的最长子数组查出即可。
 * 数组中只有1和0的话，也同理把0改为-1，计算出累加和为0的最长子数组即可。
 * 数组中只有0、1、2的话，求1、2数量相等的最长子数组，把2变-1，技巧同理。
 */
public class LongestSumSubArrayLength {

    public static void main(String[] args) {

    }

    private static int maxLength(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);  //important
        int len = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - aim)) {
                len = Math.max(i - map.get(sum - aim), len);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }
}
