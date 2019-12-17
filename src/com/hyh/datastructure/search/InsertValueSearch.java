package com.hyh.datastructure.search;
//插值查找
public class InsertValueSearch {
    public static void main(String[] args) {
        /*int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }*/
        int[] arr = {0, 2, 3, 6, 9, 15, 16, 17, 35, 36, 107, 388, 996};
        System.out.println("insertValueSearch(arr, 388, 0, arr.length - 1) = " + insertValueSearch(arr, 388, 0, arr.length - 1));
    }

    private static int insertValueSearch(int[] arr, int value, int L, int R) {
        //注意value < arr[0] 与 value > arr[arr.length - 1]这两个条件必须有 不但可以起到优化得作用
        //还可以防止midIndex越界
        if (L > R || value < arr[0] || value > arr[arr.length - 1]) {
            return -1;
        }
        //等斜率 要在斜率近似线性时效果更明显 即数据分布比较均匀得情况
        // 如果斜率没有规律得情况 即数据跳跃性很大时 可能时间比二分查找还要长时间
        int midIndex = L + (R - L) * (value - arr[L]) / (arr[R] - arr[L]);
        System.out.println("midIndex = " + midIndex);
        if (arr[midIndex] > value) {
            //return 接到返回值 结束方法
            return insertValueSearch(arr, value, L, midIndex - 1);
        }else if (arr[midIndex] < value) {
            return insertValueSearch(arr, value, midIndex + 1, R);
        }else {
            return midIndex;
        }
    }
}
