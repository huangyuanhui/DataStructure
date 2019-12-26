package com.hyh.datastructure.advance.bfprt;

import java.util.Arrays;

//给你一个无序数组中，返回其中第K小的数（要求时间复杂度O(n)）

/**
 * 快排改进
 * 这道题可以利用荷兰国旗改进的partition和随机快排的思想：随机选出一个数，将数组以该数作比较划
 * 分为<,=,>三个部分，则=部分的数是数组中第几小的数不难得知，接着对<（如果第K小的数在<部分）或>（如
 * 果第K小的数在>部分）部分的数递归该过程，直到=部分的数正好是整个数组中第K小的数。这种做法不难
 * 求得时间复杂度的数学期望为O(N），总有一半会舍弃。但这毕竟是数学期望，在实际工程中的表现可能会
 * 有偏差，而BFPRT算法能够做到时间复杂度就是O严格的(O(n))
 * <p>
 * BFPRT算法，接收一个数组和一个K值，返回数组中的一个数  function bfprt(arr, k)
 * 这个算法就是找到一个很优越的划分值 因为这个划分值使得数组中大于这个数的最少有十分之三
 * 小于这个数的也是至少有十分之三
 * 1：分组，数组被划分为了N/5个小部分，最后不足5个元素自成一个部分；
 * 排序，对每一个组进行组内排序，每个部分的5个数排序需要O(1)，所有部分排完需要O(N/5)=O(N)
 * 2：取出每个组的中位数，组成一个新数组，一共有N/5个，
 * 递归调用BFPRT算法，得到这些数中第(N/5)/2小的数（即这些数的中位数），记为pivot，这个pivot就是我们的划分值
 * 3：以pivot作为比较，将整个数组划分为<pivot , =pivot , >pivot三个区域
 * 4：判断第K小的数在哪个区域，如果在=区域则直接返回pivot，如果在<或>区域，则将这个区域的数递归调用BFPRT算法
 * 5：base case：在某次递归调用BFPRT算法时发现这个区域只有一个数，那么这个数就是我们要找的数。
 */
public class BTPRTDemo {

    public static void main(String[] args) {
        int[] arr = {6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9};
        System.out.println("getMinKNumsByBFPRT(arr, 13) = " + Arrays.toString(getMinKNumsByBFPRT(arr, 13)));
    }


    //获取数组中第K小的数
    private static int[] getMinKNumsByBFPRT(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return arr;
        }
        //获得我们想要的数组中第K小的数
        int minKthNum = getMinKthByBFPRT(arr, k);

        //数组中所有比第K小的数小的数
        int[] res = new int[k];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minKthNum) {
                res[index++] = arr[i];
            }
        }
        //看是否需要补齐
        for (; index < res.length; index++) {
            res[index] = minKthNum;
        }
        return res;
    }

    //KFPRT算法获取第K小的数
    private static int getMinKthByBFPRT(int[] arr, int k) {
        int[] copyArr = Arrays.copyOf(arr, arr.length);
        //为什么要k-1 数组是从0开始的 如数组第一个数就是arr[0]
        return bfprtByHyh(copyArr, 0, copyArr.length - 1, k - 1);
    }

    private static int bfprtByHyh(int[] arr, int start, int end, int k) {
        if (start == end) {
            return arr[start];
        }
        //我们要找的优越的划分值
        int pivot = medianOfMedians(arr, start, end);

        //partition 荷兰国旗算法 数组中小于划分值pivot的放左边 等于pivot的放中间 大于pivot的放右边
        //返回拍好序的数组中等于pivot的区域
        int[] pivotRange = partition(arr, start, end, pivot);

        if (k >= pivotRange[0] && k <= pivotRange[1]) {
            return arr[k];
        }else if (k > pivotRange[1]) {
            return bfprtByHyh(arr, pivotRange[1] + 1, end, k);
        }else {
            return bfprtByHyh(arr, start, pivotRange[0] - 1, k);
        }
    }

    //获取优越的划分值
    public static int medianOfMedians(int[] arr, int start, int end) {
        int num = end - start + 1;
        int offset = num % 5 == 0 ? 0 : 1;
        //中位数数组
        int[] medians = new int[num / 5 + offset];
        //赋值
        for (int i = 0; i < medians.length; i++) {
            int beginI = start + i * 5;
            int endI = beginI + 4;
            medians[i] = getMedian(arr, beginI, Math.min(endI, end));
        }
        return bfprtByHyh(medians, 0, medians.length - 1, medians.length / 2);
    }

    //获取分组中的中位数
    public static int getMedian(int[] arr, int begin, int end) {
        //对分组排序
        insertionSort(arr, begin, end);
        int sum = end + begin;
        int mid = (sum / 2) + (sum % 2);
        return arr[mid];
    }

    //插入排序
    public static void insertionSort(int[] arr, int begin, int end) {
        if (begin >= end) {
            return;
        }
        for (int i = begin + 1; i <= end; i++) {
            for (int j = i; j > begin; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    //快速排序
    public static int[] partition(int[] arr, int begin, int end, int pivot) {
        int L = begin - 1;
        int R = end + 1;
        while (begin != R) {
            if (arr[begin] > pivot) {
                swap(arr, begin, --R);
            } else if (arr[begin] < pivot) {
                swap(arr, begin++, ++L);
            } else {
                begin++;
            }
        }
        return new int[]{L + 1, R - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
