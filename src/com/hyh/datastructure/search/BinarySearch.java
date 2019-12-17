package com.hyh.datastructure.search;

import java.util.ArrayList;
import java.util.List;

//二分查找（要求：要查找的数组必须是有序的）
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {0, 2, 3, 6, 9, 15, 16, 17, 35, 36, 107, 388, 996};
        //System.out.println("binarySearch2(arr, 388, 0, arr.length - 1) = " + binarySearch2(arr, 388, 0, arr.length - 1));
        System.out.println("binarySearch(arr, 388, 0, arr.length - 1) = " + binarySearch(arr, 388, 0, arr.length - 1));
    }

    private static int binarySearch(int[] arr, int value, int L, int R) {
        if (L > R) {
            return -1;
        }
        int midIndex = L + (R - L) / 2;
        System.out.println("midIndex = " + midIndex);
        if (arr[midIndex] > value) {
            //return 接到返回值 结束方法
            return binarySearch(arr, value, L, midIndex - 1);
        }else if (arr[midIndex] < value) {
            return binarySearch(arr, value, midIndex + 1, R);
        }else {
            return midIndex;
        }
    }

    //用二分法返回数组中所有与要查找数相等的数
    private static List<Integer> binarySearch2(int[] arr, int value, int L, int R) {
        if (L > R) {
            return null;
        }
        int midIndex = L + (R - L) / 2;
        if (arr[midIndex] > value) {
            //return 接到返回值 结束方法
            return binarySearch2(arr, value, L, midIndex - 1);
        }else if (arr[midIndex] < value) {
            return binarySearch2(arr, value, midIndex + 1, R);
        }else {
            //二分如果能找到 总会在一个位置找到 然后在这个位置左右扫描 得到结果返回 层层return
            List<Integer> list = new ArrayList<>();
            //向扫描
            int temp = midIndex - 1;
            while (temp >= 0 && arr[temp] == value) {
                list.add(temp--);
            }
            list.add(midIndex);
            temp = midIndex + 1;
            while (temp < arr.length && arr[temp] == value) {
                list.add(temp++);
            }
            return list;
        }
    }
}
