package com.hyh.datastructure.advance.searchtree;

/**
 * 判断是否是一个平衡二叉树
 */
public class IsBalanceTreeDemo {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class ReturnType {
        public boolean isBalance;
        public int high;
        public ReturnType(boolean isBalance, int high) {
            this.isBalance = isBalance;
            this.high = high;
        }
    }

    public static ReturnType process(Node head) {
        if (head == null) {
            return new ReturnType(true, 0);
        }
        ReturnType leftReturnType = process(head.left);
        if (!leftReturnType.isBalance) {
            return new ReturnType(false, 0);
        }
        ReturnType rightReturnType = process(head.right);
        if (!rightReturnType.isBalance) {
            return new ReturnType(false, 0);
        }
        return new ReturnType(true, Math.max(leftReturnType.high, rightReturnType.high) + 1);
    }

    public static boolean isBalance(Node head) {
        return process(head).isBalance;
    }
}
