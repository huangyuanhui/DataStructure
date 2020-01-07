package com.hyh.datastructure.sort;

//逆序对问题  在一个数组中，左边的数如果比右边的数大，则这两个数构成一个逆序对，请打印所有逆序对
public class InversionPairDemo {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int midIndex = L + (R - L) / 2;
        mergeSort(arr, L, midIndex);
        mergeSort(arr, midIndex + 1, R);
        merge(arr, L, midIndex, R);
    }

    private static void merge(int[] arr, int L, int midIndex, int R) {
        int[] tempArr = new int[R - L + 1];
        int left = L;
        int right = midIndex + 1;
        int index = 0;
        while (left <= midIndex && right <= R) {
            if (arr[left] < arr[right]) {
                int temp = right;
                for (int i = 0; i <= R - right; i++) {
                    System.out.println("逆序对：");
                    System.out.print("arr[" + left + "] = " + arr[left]);
                    System.out.println("  arr[" + (temp + i) + "] = " + arr[temp + i]);

                }
            }
            tempArr[index++] = arr[left] < arr[right] ? arr[left++] : arr[right++];
        }

        while (left <= midIndex) {
            tempArr[index++] = arr[left++];
        }
        while (right <= R) {
            tempArr[index++] =arr[right++];
        }
        for (int i = 0; i < tempArr.length; i++) {
            arr[L + i] = tempArr[i];
        }
    }
}
