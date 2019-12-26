package com.hyh.datastructure.advance.manacher;

import java.util.Arrays;

//str的最大回文子串的半径
public class ManacherDemo {
    public static void main(String[] args) {
        String str1 = "abccbakkacbccbcacdd";
        System.out.println("getMaxLcpsLengthWithManacher(str1) = " + getMaxLcpsLengthWithManacher(str1));
    }

    private static char[] manacherStr(String str1) {
        char[] str1Chars = str1.toCharArray();
        char[] str2Chars = new char[2 * str1Chars.length + 1];
        int index = 0;
        for (int i = 0; i < str2Chars.length; i++) {
            str2Chars[i] = (i & 1) == 0 ? '#' : str1Chars[index++];
        }
        return str2Chars;
    }

    //用manacher算法获取字符串最大回文
    private static int getMaxLcpsLengthWithManacher(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }
        char[] charArr = manacherStr(str);
        //每个位置的回文半径
        int[] pArr = new int[charArr.length];
        //回文中心
        int C = -1;
        //回文右边界 初始回文右边界是-1
        int R = -1;

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < charArr.length; i++) {
            //2 * C - i 即 i 位置相对于回文中心C的 i'
            //pArr[2 * C - i] 就是 i' 位置的回文半径
            //所以pArr[i]表示的是i位置起码的回文半径
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;

            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
                //暴力扩回文半径
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }

            //看是否需要更新回文右边界R以及回文中心C （i位置的回文右边界 与 R比较）
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            //记录最大回文半径
            max = Math.max(max, pArr[i]);
        }
        //这里就是回文直径啦（不要忘了我们加了虚轴#）
        return max - 1;
    }
}
