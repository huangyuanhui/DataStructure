package com.hyh.datastructure.sort;

import java.util.Arrays;

//插入排序
public class InsertSort {
    public static void main(String[] args) {
        //int[] arr = {8, 2, 5, -1, 9, 3, 7, 4, 6, 1};
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();
        insertSort(arr);
        long end = System.currentTimeMillis();
        //O(n^2) 大概880
        System.out.println("排序花费时间：" + (end - start) + "毫秒");

        //System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }

    private static void insertSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j > 0 && arr[j] < arr[j - 1]; j--) {
                temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }
        }
    }
}

