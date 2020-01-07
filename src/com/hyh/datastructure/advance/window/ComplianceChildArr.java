package com.hyh.datastructure.advance.window;

import java.util.LinkedList;

/**
 * O（N）
 * 给你一个整型数组，判断其所有子数组中最大值和最小值的差值不超过num（如果满足则称该数组达标）的子数组个数。
 * （子数组指原数组中任意个连续下标上的元素组成的数组）
 * <p>
 * 暴力解：遍历每个元素，再遍历以当前元素为首的所有子数组，再遍历子数组找到其中的最大值和最小值以
 * 判断其是否达标。很显然这种方法的时间复杂度为o(N^3)，但如果使用最大值更新结构，则能实现O(N)级
 * 别的解。
 * <p>
 * 我们利用这个结论：
 * 如果使用L和R两个指针指向数组的两个下标，且L在R的左边。
 * 1：当L~R这一子数组达标时，可以推导出以L开头的长度不超过R-L+1的所有子数组均达标；
 * 2：当L~R这一子数组不达标时，无论L向左扩多少个位置或者R向右扩多少个位置，L~R还是不达标。
 * <p>
 * O(N)的解对应的算法是：L和R都从0开始，R先向右移动，R每右移一个位置就使用最大值更新结构和最小值
 * 更新结构记录一下L~R之间的最大值和最小值的下标，当R移动到如果再右移一个位置L~R就不达标了时停止，
 * 这时以当前L开头的长度不超过R-L+1的子数组都达标；然后L右移一个位置，同时更新一下最大值、最小值
 * 更新结构（L-1下标过期了），再右移R至R如果右移一个位置L~R就不达标了停止（每右移R一次也更新最大、
 * 小值更新结构）……；直到L到达数组尾元素为止。将每次R停止时，R-L+1的数量累加起来就是O(N)的解，
 * 因为L和R都只向右移动，并且每次R停止时，以L开头的达标子串的数量直接通过R-L+1计算，所以时间复杂
 * 度就是将数组遍历了一遍即O(N)
 */
public class ComplianceChildArr {
    public static int getComplianceChildArr(int arr[], int num) {
        //最大值、最小值更新结构
        LinkedList<Integer> maxQ = new LinkedList<>();
        LinkedList<Integer> minQ = new LinkedList<>();
        int L = 0;
        int R = 0;
        maxQ.add(0);
        minQ.add(0);
        int res = 0;
        while (L < arr.length) {
            while (R < arr.length - 1) {
                while (!maxQ.isEmpty() && arr[maxQ.getLast()] <= arr[R + 1]) {
                    maxQ.pollLast();
                }
                maxQ.add(R + 1);
                while (!minQ.isEmpty() && arr[minQ.getLast()] >= arr[R + 1]) {
                    minQ.pollLast();
                }
                minQ.add(R + 1);

                if (arr[maxQ.peekFirst()] - arr[minQ.peekFirst()] > num) {
                    break;
                }
                R++;
            }
            res += (R - L + 1);
            if (maxQ.peekFirst() == L) {
                maxQ.pollFirst();
            }
            if (minQ.peekFirst() == L) {
                minQ.pollFirst();
            }
            L++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5};
        System.out.println(getComplianceChildArrCount(arr, 3));//9
    }

    private static int getComplianceChildArrCount(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        //最大值的窗口更新结构
        LinkedList<Integer> maxQ = new LinkedList<>();
        //最小值的窗口更新结构
        LinkedList<Integer> minQ = new LinkedList<>();
        int L = 0;
        int R = 0;
        int count = 0;
        while (L < arr.length) {
            while (R < arr.length) {
                //最大值的窗口加数逻辑(从大到小 保证L-R范围内最大的在双端队列maxQ头)
                while (!maxQ.isEmpty() && arr[maxQ.peekLast()] <= arr[R]) {
                    maxQ.pollLast();
                }
                maxQ.addLast(R);

                //最小值的窗口加数逻辑(从小到大 保证L-R范围内最小的在双端队列minQ头)
                while (!minQ.isEmpty() && arr[minQ.peekLast()] >= arr[R]) {
                    minQ.pollLast();
                }
                minQ.addLast(R);

                if (arr[maxQ.peekFirst()] - arr[minQ.peekFirst()] > num) {
                    break;
                }
                R++;
            }
            //看下标是否过期 因为L-R范围已经搞定 如果双端队列maxQ头的值是L,即L-R范围最大是arr[L]
            if(maxQ.peekFirst() == L) {
                maxQ.pollFirst();
            }
            //看下标是否过期 因为L-R范围已经搞定 如果双端队列minQ头的值是L,即L-R范围最小是arr[L]
            if (minQ.peekFirst() == L) {
                minQ.pollFirst();
            }
            count += R - L;
            //更新L
            L++;
        }
        return count;
    }

}
