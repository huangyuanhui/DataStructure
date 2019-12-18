package com.hyh.datastructure.sort;

import java.util.Arrays;

//堆排序(可认为一个堆在一个数组上伸（heapInsert）缩(heapify) 完成堆排序)
public class HeapSort {
    public static void main(String[] args) {
       /* int[] arr = {4, 6, 8, 5, 9};
        //让数组形成大根堆 O(n)
        heapSort(arr);

        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));*/

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();
        heapSort(arr);
        long end = System.currentTimeMillis();
        //O(n^2) 大概12ms
        System.out.println("排序花费时间：" + (end - start) + "毫秒");
    }

    private static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            //往下沉 再形成大根堆
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }

    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            //左右节点值大的索引
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            //与index比较
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = 2 * index + 1;
        }
    }

    private static void heapInsert(int[] arr, int index) {
        int temp = 0;
        //index不会是负数 因为当arr[0] > arr[(1 - 1) /2] 或 arr[0] > arr[(2 -1) / 2] 时 会跳出循环
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
