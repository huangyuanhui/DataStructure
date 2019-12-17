package com.hyh.datastructure.sort;

import java.util.Arrays;

//希尔排序（改进的插入排序）
public class ShellSort {
    public static void main(String[] args) {
        /*int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr);*/

        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();
        shellSort(arr);
        long end = System.currentTimeMillis();
        //大概5200
        System.out.println("排序花费时间：" + (end - start) + "毫秒");
        //System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }

    //循环实现希尔排序(完成版本)
    private static void shellSort(int[] arr) {
        int temp = 0;
        for (int stepLength = arr.length / 2; stepLength > 0; stepLength /= 2) {
            //之前拍好的 后面都会利用起来哦
            for (int i = stepLength; i < arr.length; i++) {
                //所谓插入排序仅仅就是从后往前排序
                for (int j = i - stepLength; j >= 0 && arr[j] > arr[j + stepLength]; j -= stepLength) {
                    temp = arr[j];
                    arr[j] = arr[j + stepLength];
                    arr[j + stepLength] = temp;
                }
            }
        }
    }

    //循环实现希尔排序（移位法）
    public static void shellSortImprove(int[] arr) {

    }
}
