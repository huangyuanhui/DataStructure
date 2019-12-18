package com.hyh.datastructure.sort;

import java.util.Arrays;

//基数排序(桶排序的扩展，稳定性极好, 空间换时间的经典算法）
public class RadixSort {
    public static void main(String[] args) {
        /*int[] arr = {80, 4, 155, 774, 10010, 3067, 666, 2};
        radixSort(arr);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));*/

        /*int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();
        radixSort(arr);
        long end = System.currentTimeMillis();
        //O(n^2) 大概80ms
        System.out.println("排序花费时间：" + (end - start) + "毫秒");*/

        int[] arr = {80, 4, 155, 774, 10010, 3067, 666, 2};
        System.out.println("maxGap(arr, arr.length) = " + maxGap(arr, arr.length));
    }

    private static void radixSort(int[] arr) {
        int[][] bucket = new int[10][arr.length];
        //用来记录每个桶存放的实际有效数据指针
        int[] bkEleCount = new int[10];

        for (int i = 0; i < getDigitCount(getMax(arr)); i++) {
            for (int j = 0; j < arr.length; j++) {
                int digit = arr[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                bucket[digit][bkEleCount[digit]] = arr[j];
                bkEleCount[digit]++;
            }

            int index = 0;
            for (int r = 0; r < bucket.length; r++) {
                //小优化 有数据才取
                if (bkEleCount[r] > 0) {
                    for (int c = 0; c < bkEleCount[r]; c++) {
                        arr[index++] = bucket[r][c];
                    }
                    //每个桶取完后 桶中有效数据个数置为0
                    bkEleCount[r] = 0;
                }
            }
        }
    }

    private static int getDigitCount(int max) {
        /*int i = 10;
        int digit = 1;
        while (true) {
            if (max / i == 0) {
                break;
            }
            i *= 10;
            digit++;
        }
        return digit;*/
        return ("" + max).length();
    }

    private static int getMax(int[] arr) {
        int max = 0;
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }


    //延伸：给定一个数组，求排序之后 相邻两数的最大差值 要求时间复杂度O(N) 且基于非比较的方法实现
    private static int maxGap(int[] arr, int len) {
        if (arr == null || len < 2) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            max = max > arr[i] ? max : arr[i];
            min = min < arr[i] ? min : arr[i];
        }
        if (max == min) {
            return 0;
        }

        int[] maxArr = new int[len + 1];
        int[] minArr = new int[len + 1];
        boolean[] hasNum = new boolean[len + 1];
        minArr[0] = min;
        hasNum[0] = true;

        maxArr[maxArr.length - 1] = max;
        hasNum[hasNum.length - 1] = true;

        int index = 0;
        for (int i = 0; i < len; i++) {
            //抠边界
            index = (len) * (arr[i] - min) / (max - min);
            maxArr[index] = hasNum[index] ? Math.max(arr[i], maxArr[index]) : arr[i];
            minArr[index] = hasNum[index] ? Math.min(arr[i], maxArr[index]) : arr[i];
            hasNum[index] = true;
        }

        int res = 0;
        int lastMax = maxArr[0];
        for (int i = 0; i <= len; i++) {
            if (hasNum[i]) {
                res =  Math.max(minArr[i] - lastMax, res);
                lastMax = maxArr[i];
            }
        }
        return res;
    }

}
