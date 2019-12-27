package com.hyh.datastructure.advance.window;

import java.util.LinkedList;

/**
 * 给定一个数组和一个固定大小的窗口，求这个窗口从左向右移动时，每次窗口中最大的数。O(N)
 * 重点在于如何更新窗口中的数。必须使窗口中存储严格单调递减（相等都不行）的数。最左边保持最大的。
 * 当不更新了且达到滑动窗口的空间，就要开始丢弃最旧的数，也就是更新左边
 *
 * 我们可以用双端队列实现它 此结构可以使用一个双端队列来实现，一端只用来放数据（放数据之前
 * 的检查过程可能会弹出其他数据），另一端用来获取目前为止出现过的最大值
 *
 * 当然我们也可以实现窗口大小是任意变化的 加数逻辑不变 减数逻辑更简单 只需要丢弃双端队列头的数据即可
 * 功能更强大
 */
public class GetMaxWindows {
    public static int[] getMaxWindows(int[] arr, int w) {
        int[] list = new int[arr.length - w + 1];//保存结果
        LinkedList<Integer> deque = new LinkedList<>();//保存一个从左到右为从大到小的双端队列。值为数组的下标
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i])//如果右端的元素比当前要加入的元素下，就一直弹出
            {
                deque.pollLast();
            }
            deque.add(i);//将i加入
            if (i - deque.peekFirst() == w)//将过期的删除,（值比当前的最右端小，且达到双端队列的最大值，则左边弹出）
            {
                deque.pollFirst();
            }
            if (i >= w - 1)//当达到滑动窗口的值时，开始收集
            {
                list[index++] = arr[deque.peekFirst()];//最大值收集出来
            }

        }
        return list;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        int[] maxWindows = getMaxWindow(arr, 3);
        for (int i = 0; i < maxWindows.length; i++) {
            System.out.print(maxWindows[i] + " ");
        }

    }



    //w : 窗口大小
    private static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || arr.length == 0 || arr.length < w) {
            return null;
        }
        //双端队列
        LinkedList<Integer> qMax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            //窗口加数逻辑
            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[i]) {
                qMax.pollLast();
            }
            qMax.addLast(i);
            //窗口减数逻辑
            if (qMax.peekFirst() == i - w) {
                qMax.pollFirst();
            }
            if (i >= w - 1) {
                res[index++] = arr[qMax.peekFirst()];
            }
        }
        return res;
    }

}
