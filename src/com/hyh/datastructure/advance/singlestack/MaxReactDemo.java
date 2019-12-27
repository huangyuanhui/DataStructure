package com.hyh.datastructure.advance.singlestack;

import java.util.Stack;

/**
 * 求一个矩阵最大子矩阵
 */
public class MaxReactDemo {
    public static void main(String[] args) {

    }

    public static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        //栈底到栈顶严格从小到大
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();
                //栈为空 可以到达最左边 不为空
                int k = stack.isEmpty() ? -1 : stack.peek();
                int currArea = height[j] * (i - j - 1);
                maxArea = Math.max(maxArea, currArea);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int i = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int currArea = (height.length - k - 1) * height[i];
            maxArea = Math.max(currArea, maxArea);
        }
        return maxArea;
    }
}
