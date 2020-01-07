package com.hyh.datastructure.sort;

//小和问题 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组的小和。
public class SmallSumDemo {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
        System.out.println("mergeSort(arr, 0, arr.length - 1) = " + mergeSort(arr, 0, arr.length - 1));
    }

    //归并排序
    private static int mergeSort(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + (R - L) / 2;
        return mergeSort(arr, L, mid) + mergeSort(arr, mid + 1, R) + merge(arr, L, mid, R);
    }

    //合并
    private static int merge(int[] arr, int L, int midIndex, int R) {
        int[] tempArr = new int[R - L + 1];
        int i = L;
        int j = midIndex + 1;
        int index = 0;
        int sum = 0;
        while (i <= midIndex && j <= R) {
            sum += arr[i] < arr[j] ? arr[i] * (R - j + 1) : 0;
            tempArr[index++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        }

        while (i <= midIndex) {
            tempArr[index++] = arr[i++];
        }
        while (j <= R) {
            tempArr[index++] = arr[j++];
        }
        for (i = 0; i < tempArr.length; i++) {
            arr[L + i] = tempArr[i];
        }
        return sum;
    }
}
