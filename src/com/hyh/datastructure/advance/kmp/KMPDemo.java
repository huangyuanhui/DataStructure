package com.hyh.datastructure.advance.kmp;

//str2是否是str1的子串 如果是 请返回str2在str1开始的位置
public class KMPDemo {
    public static void main(String[] args) {
        String str1 = "aabbccabcabcaaabbcc";
        String str2 = "abcabca";
        System.out.println("getIndexWithKMP(str1, str2) = " + getIndexWithKMP(str1, str2));
    }

    //KMP算法
    private static int getIndexWithKMP(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() < 1 || str2.length() < 1) {
            return -1;
        }

        char[] str1CharArr = str1.toCharArray();
        char[] str2CharArr = str2.toCharArray();

        int index1 = 0;
        int index2 = 0;
        int[] maxPrefixAndMaxSuffixCount = returnMaxPrefixAndMaxSuffix(str2CharArr);

        while (index1 < str1CharArr.length && index2 < str2CharArr.length) {
            if (str1CharArr[index1] == str2CharArr[index2]) {
                index1++;
                index2++;
            } else if (maxPrefixAndMaxSuffixCount[index2] == -1) {
                //index2回到了0位置
                index1++;
            } else {
                //回到最长前缀的后一个位置 再去比较
                index2 = maxPrefixAndMaxSuffixCount[index2];
            }
        }
        return index2 == str2CharArr.length ? index1 - index2 : -1;
    }

    //获取一个字符串每个位置的字符的最长前缀和最长后缀
    private static int[] returnMaxPrefixAndMaxSuffix(char[] strChars) {
        if (strChars.length == 1) {
            return new int[]{-1};
        }
        int[] maxPrefixAndMaxSuffixCount = new int[strChars.length];
        maxPrefixAndMaxSuffixCount[0] = -1;
        maxPrefixAndMaxSuffixCount[1] = 0;
        //index索引前一个字符的的最长前缀最长后缀的下一个位置
        int cn = 0;
        int index = 2;
        while (index < maxPrefixAndMaxSuffixCount.length) {
            if (strChars[index - 1] == strChars[cn]) {
                maxPrefixAndMaxSuffixCount[index++] = ++cn;
            } else if (cn > 0) {
                //cn变为cn位置字符的最长前缀与最长后缀
                cn = maxPrefixAndMaxSuffixCount[cn];
            } else {
                //最长前缀与最长后缀为0
                maxPrefixAndMaxSuffixCount[index++] = 0;
            }
        }
        return maxPrefixAndMaxSuffixCount;
    }
}
