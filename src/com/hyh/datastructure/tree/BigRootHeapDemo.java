package com.hyh.datastructure.tree;

import java.util.Arrays;

//大根堆（堆就是完全二叉树）
public class BigRootHeapDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3 ,4, 5};
        //数组形成大根堆 （O(n)）
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));

        arr[0] = 2;
        heapify(arr, 0);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }

    //在一个大根堆中插入一个节点  往上调整 重新变成大根堆 (作用大大滴：求不断增加数组中的中位数)
    private static void heapInsert(int[] arr, int index) {
        //条件判断不够简洁
        /*while ((index - 1) / 2 >= 0 && arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }*/

        //简洁
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    //大根堆中某一个值变小了 向下调整 重新形成大根堆
    private static void heapify(int[] arr, int index) {
        int left = 2 * index + 1;
        while (left < arr.length) {
            int largest = left + 1 < arr.length && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                //说明arr[index]大于等于他的左右子节点中较大的节点，调整完成
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = 2 * index + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
