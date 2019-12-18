package com.hyh.datastructure.sort;

import java.util.Arrays;
import java.util.Random;

//快速排序(要比希尔排序快的 快排做不到稳定性 因为partition的过程做不到稳定性)
public class QuickSort {
    public static void main(String[] args) {
        //int[] arr = {8, 2, 5, -1, 9, 3, 7, 4, 6, 1};
        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        //O(n^2) 大概880
        System.out.println("排序花费时间：" + (end - start) + "毫秒");
        System.out.println("arr.length = " + arr.length);
    }


    private static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            //随机快排的额外空间复杂度logN 就是要记住这个划分点
            int[] pivot = partition(arr, L, R);
            quickSort(arr, L, pivot[0] - 1);
            quickSort(arr, pivot[1] + 1, R);
        }
    }

    //荷兰国旗问题
    private static int[] partition(int[] arr, int L, int R) {
        int less = L - 1;
        int more = R + 1;
        int currIndex = L;
        //没有下面这一行是经典快排 有了下面这行是随机快排（随即快排是最常用的排序算法 因为代码很简洁 代码很简洁
        // ，说明常数项操作很少 mergeSort输在要准备一个额外的数组与数组的拷贝）
        int partitionNum = arr[L + (int) (Math.random() * (R - L + 1))];
        while (currIndex < more) {
            if (arr[currIndex] < partitionNum) {
                swap(arr, ++less, currIndex++);
            } else if (arr[currIndex] > partitionNum) {
                swap(arr, --more, currIndex);
            } else {
                currIndex++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    //荷兰国旗问题
    private static void partition(int[] arr, int L, int R, int partitionNum) {
        int less = L - 1;
        int more = R + 1;
        int currIndex = L;
        while (currIndex < more) {
            if (arr[currIndex] < partitionNum) {
                swap(arr, ++less, currIndex++);
            } else if (arr[currIndex] > partitionNum) {
                swap(arr, --more, currIndex);
            } else {
                currIndex++;
            }
        }
    }

    //交换
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}



