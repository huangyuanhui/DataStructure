package com.hyh.datastructure.sort;

import java.util.Arrays;

//荷兰国旗问题
public class DutchFlagDemo {
    public static void main(String[] args) {
        int[] arr = {8, 6, 5, 1, 3, 5, 2, 7, 5, 4, 9};
        int[] ints = partition2(arr, 0, arr.length - 1, 5);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println("Arrays.toString(ints) = " + Arrays.toString(ints));

    }

    //小于等于partitionNum的放左边 大于partitionNum的放右边
    private static void partition1(int[] arr, int L, int R, int partitionNum) {
        int lessEqual = -1;
        int index = L;
        while (index <= R) {
            if (arr[index] <= partitionNum) {
                swap(arr, index++, ++lessEqual);
            }else {
                index++;
            }
        }
    }

    //小于partitionNum的放左边 等于partitionNum的放中间 大于partitionNum的放右边
    private static int[] partition2(int[] arr, int L, int R, int partitionNum) {
        int less = L - 1;
        int more = R;
        while (L < more) {
            if (arr[L] < partitionNum) {
                swap(arr, L++, ++less);
            }else if (arr[L] > partitionNum) {
                swap(arr, L, --more);
            }else {
                L++;
            }
        }
        //返回等于区域
        return new int[]{less + 1, more - 1};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
