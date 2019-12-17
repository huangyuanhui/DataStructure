package com.hyh.datastructure.search;

//线性查找
public class LinkedSearch {
    public static void main(String[] args) {
        int[] arr = {5, -1, 9, 3, 7};
        System.out.println("search(arr, 3) = " + search(arr, 3));
    }

    private static int search(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
