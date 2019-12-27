package com.hyh.datastructure.advance.singlestack;

import java.util.Stack;

/**
 * 求一个矩阵最大子矩阵
 */
public class MaxReactDemo {
    public static void main(String[] args) {
        int matrix[][] = {
                {1, 1, 1, 0, 1},
                {0, 1, 0, 1, 1},
                {1, 0, 0, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 1, 1}
        };
        System.out.println("maxRecSizeOfMatrix(matrix) = " + maxRecSizeOfMatrix(matrix));
    }

    private static int maxRecSizeOfMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int maxSize = 0;
        int[] height = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                height[j] = matrix[i][j] == 0 ? 0 : height[j] + matrix[i][j];
            }
            maxSize = Math.max(maxSize, maxRecFromBottom(height));
        }
        return maxSize;
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
