package com.hyh.datastructure.advance.searchtree;

/**
 * 求一颗树的最小与最大
 */
public class MaxMinValue {
    public static void main(String[] args) {

    }

    public static class Node {
        public int value;
        public Node right;
        public Node left;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class ReturnData {
        public int max;
        public int min;

        public ReturnData(int max, int min) {
            this.max = max;
            this.min = min;
        }
    }

    public static ReturnData process(Node head) {
        if (head == null) {
            //不影响决策 最小值给系统最大 最大值给系统最小
            new ReturnData(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        ReturnData leftData = process(head.left);
        ReturnData rightData = process(head.right);
        return new ReturnData(
                Math.max(Math.max(leftData.max, rightData.max), head.value),
                Math.min(Math.min(leftData.min, rightData.min), head.value));
    }
}
