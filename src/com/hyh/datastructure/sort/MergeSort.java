package com.hyh.datastructure.sort;

import java.util.Arrays;

//归并排序
public class MergeSort {
    public static void main(String[] args) {
        /*int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));*/

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        //O(n^2) 大概15ms
        System.out.println("排序花费时间：" + (end - start) + "毫秒");
    }

    //归并排序
    private static void mergeSort(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int midIndex = L + ((R - L) >> 1);
        //分（很难栈溢出 栈中方法数为logN）
        mergeSort(arr, L, midIndex);
        mergeSort(arr, midIndex + 1, R);
        //治
        merge(arr, L, midIndex, R);
    }

    //并
    private static void merge(int[] arr, int L, int midIndex, int R) {
        //归并排序需要一个额外的空间 额外空间复杂度为O(N)
        int[] tempArr = new int[R - L + 1];
        int i = L;
        int j = midIndex + 1;
        int index = 0;
        while (i <= midIndex && j <= R) {
            tempArr[index++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        }

        //只会走一个
        while (i <= midIndex) {
            tempArr[index++] = arr[i++];
        }

        //只会走一个
        while (j <= R) {
            tempArr[index++] = arr[j++];
        }

        for (i = 0; i < tempArr.length; i++) {
            arr[L + i] = tempArr[i];
        }
    }
}
