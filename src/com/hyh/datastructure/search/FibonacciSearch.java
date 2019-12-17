package com.hyh.datastructure.search;

import java.util.Arrays;

//斐波那契（黄金分割）查找(数组也是有序的)
//斐波那契查找的时间复杂度还是O(log2n)，但是与折半查找相比，斐波那契查找的优点是它只涉及加法和减法运算，
// 而不用除法，而除法比加减法要占用更多的时间，因此，斐波那契查找的运行时间理论上比折半查找小，
// 但是还是得视具体情况而定
public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println("fibonacciSearch(arr, 89) = " + fibonacciSearch(arr, 1024));
    }

    private static int fibonacciSearch(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        //mid就是划分值 mid = low + f(k - 1) - 1;  low是下标  f(k -1)是数组个数，所以要减1
        int mid = 0;
        //表示斐波那契数组分割数组得下标 f(k) = f(k - 1) + f(k - 2)
        int k = 0;
        //我们给20 足够大了
        int[] fibArr = fib(20);
        //获得斐波那契数 是这样一个数（第k个斐波那契数的值是等于或者将将正好大于要查找数组的长度）
        while (fibArr[k] - 1 < high) {
            k++;
        }
        //判断获取的斐波那契数的值与数组长度的关系 小于数组要补足
        //此时此刻 f(k) 必定大于等于 arr.length
        int[] temp = Arrays.copyOf(arr, fibArr[k]);
        for (int i = arr.length; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        while (low <= high) {
            mid = low + fibArr[k - 1] - 1;
            if (temp[mid] > value) {
                high = mid - 1;
                k--;
            } else if (temp[mid] < value) {
                low = mid + 1;
                k -= 2;
            } else {
                //因为上面的补齐操作
                if (mid <= high) {
                    return mid;
                }
                return high;
            }
        }
        return -1;
    }

    private static int[] fib(int arrLength) {
        int[] fibArr = new int[arrLength];
        fibArr[0] = 0;
        fibArr[1] = 1;
        for (int i = 2; i < fibArr.length; i++) {
            fibArr[i] = fibArr[i - 1] + fibArr[i - 2];
        }
        return fibArr;
    }
}
