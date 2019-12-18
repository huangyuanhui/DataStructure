package com.hyh.datastructure.sort;

import java.util.Arrays;
//冒泡排序 （可以实现稳定性）
public class BubbleSort {
    public static void main(String[] args) {
        //int[] arr = {5, -1, 9, 3, 7};
        //int[] arr = {-1, 3, 5, 7, 9};
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            //随机生成 [0, 8000000)
            arr[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();
        //bubbleSort(arr);
        bubbleSortImprove(arr);
        long end = System.currentTimeMillis();
        //O(n^2) 大概9300
        System.out.println("排序花费时间：" + (end - start) + "毫秒");
    }

    //冒泡排序改进:如果在某一趟的排序中没有任何一次交换 那么此时已然有序 不需要排序了
    private static void bubbleSortImprove(int[] arr) {
        int temp = 0;
        boolean isExchange = false;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isExchange = true;
                }
            }
            //System.out.println("第" + i + "趟排序之后的数组是 : " + Arrays.toString(arr));
            if (!isExchange) {
                break;
            }else {
                isExchange = false;
            }
        }
    }

    private static void bubbleSort(int[] arr) {
        int temp = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
