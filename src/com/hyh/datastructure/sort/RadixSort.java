package com.hyh.datastructure.sort;

import java.util.Arrays;

//基数排序(桶排序的扩展，稳定性极好, 空间换时间的经典算法）
public class RadixSort {
    public static void main(String[] args) {
        /*int[] arr = {80, 4, 155, 774, 10010, 3067, 666, 2};
        radixSort(arr);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));*/

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();
        radixSort(arr);
        long end = System.currentTimeMillis();
        //O(n^2) 大概80ms
        System.out.println("排序花费时间：" + (end - start) + "毫秒");
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
}
