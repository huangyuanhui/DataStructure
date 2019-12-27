package com.hyh.datastructure.advance.singlestack;

import java.util.*;

/**
 * 单调栈
 * 单调栈是为了解决 一个数组中的每个数 左边离他最近的比他大的数 与右边理他最近的比他大的数 要求O(N)
 * <p>
 * 单调栈结构解决不规则的连续图形，最大的矩形面积。主要也跟滑动窗口类似。从栈底到栈顶保持一个严格从大到小的序列。当往栈
 * 中添加的数据不再是递增的（即大于或等于栈顶数据）就弹出栈顶数据 并收集相应信息：
 * 栈顶数据右边离他最近比他大的数就是哪个让他弹出的数
 * 栈顶数据左边离他最近比他大的数就是栈中这个数下面的数
 * <p>
 * 思路：使用一个栈，要求每次元素进栈后要维持栈中从栈底到栈顶元素值是从大到小排列的约定。将数组中的元素依次进栈，
 * 如果某次元素进栈后会违反了上述的约定（即该进栈元素比栈顶元素大），就先弹出栈顶元素，并记录该栈顶元素的信息：
 * <p>
 * 1：该元素左边离它最近的比它大的是该元素出栈后的栈顶元素，如果出栈后栈空，那么该元素左边没有比它大的数
 * 该元素右边离它最近的比它大的是进栈元素
 * 2：然后再尝试将进栈元素进栈，如果进栈后还会违反约定那就重复操作“弹出栈顶元素并记录该元素信息”，直到符合约定
 * 或栈中元素全部弹出时再将该进栈元素进栈。当数组所有元素都进栈之后，栈势必不为空，弹出栈顶元素并记录信息：
 * <p>
 * 该元素右边没有比它大的数
 * 该元素左边离它最近的比它大的数是该元素从栈弹出后的栈顶元素，如果该元素弹出后栈为空，那么该元素左边没有比它大的数
 * 由于每个元素仅进栈一次、出栈一次，且出栈时能得到题目所求信息，因此时间复杂度为O(N)
 */
public class MonotonicStackDemo {

    public static void main(String[] args) {
        int[] arr = {2, 1, 7, 5, 3, 5, 4, 9};
        findLeftAndRightBiggerNum(arr);
    }

    //
    private static void findLeftAndRightBiggerNum(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        Stack<LinkedList<Integer>> stack = new Stack<>();
        //元素进栈
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().peek()] < arr[i]) {
                LinkedList<Integer> linkedList = stack.pop();
                while (!linkedList.isEmpty()) {
                    int index = linkedList.poll();
                    System.out.println("索引为：" + index + "的元素：" + arr[index] + " 右边比它大的数是索引为：" + i + " 的" + arr[i]);
                    if (stack.isEmpty()) {
                        System.out.println("索引为：" + index + "的元素：" + arr[index] + " 没有左边比它大的数");
                    } else {
                        System.out.println("索引为：" + index + "的元素：" + arr[index] + " 左边比它大的数是索引为：" + stack.peek().peek() + " 的" + arr[stack.peek().peek()]);
                    }
                }
            }

            if (stack.isEmpty() || arr[stack.peek().peek()] != arr[i]) {
                LinkedList<Integer> linkedList = new LinkedList<>();
                linkedList.push(i);
                stack.push(linkedList);
            } else {
                stack.peek().push(i);
            }
        }

        while (!stack.isEmpty()) {
            LinkedList<Integer> linkedList = stack.pop();
            while (!linkedList.isEmpty()) {
                int index = linkedList.poll();
                System.out.println("索引为：" + index + "的元素：" + arr[index] + " 右边没有比它大的数");
                if (stack.isEmpty()) {
                    System.out.println("索引为：" + index + "的元素：" + arr[index] + " 左边没有比它大的数");
                } else {
                    System.out.println("索引为：" + index + "的元素：" + arr[index] + " 左边比它大的数是索引为：" + stack.peek().peek() + " 的" + arr[stack.peek().peek()]);
                }
            }
        }

    }
}
