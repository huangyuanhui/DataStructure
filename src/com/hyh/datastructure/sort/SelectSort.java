package com.hyh.datastructure.sort;

import java.util.Arrays;

//选择排序
public class SelectSort {
    public static void main(String[] args) {
        //int[] arr = {5, -1, 9, 3, 7};
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        //selectSort(arr);
        selectSortImprove(arr);
        long end = System.currentTimeMillis();
        //O(n^2) 大概2500
        System.out.println("排序花费时间：" + (end - start) + "毫秒");

        //System.out.println("arr = " + Arrays.toString(arr));
    }

    //其实没什么selectSortImprove区别
    private static void selectSortImprove1(int[] arr) {
        int min = 0;
        int minIndex = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }

    //只记录每一趟最小的值的索引 每趟结束完才进行交换
    private static void selectSortImprove(int[] arr) {
        int temp = 0;
        int minIndex = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    private static void selectSort(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
