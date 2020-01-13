package com.hyh.datastructure.advance.searchtree;

/**
 * 二叉树中，一个节点可以往上走和往下走，那么从节点A总能走到节点B。
 * 节点A走到节点B的距离为：A走到B最短路径上的节点个数。
 * 求一棵二叉树上的最远距离
 *
 * 列可能性：
 * 1、来自左子树最长距离
 * 2、来自右子树最长距离
 * 3、经过X的情况下的最远距离，左树最深+右树最深+1
 *
 * 收集信息：
 * 1、最长距离
 * 2、深度
 */
public class MaxDistanceTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    //返回的消息实体
    public static class ReturnType {
        public int high;
        public int maxDistance;
        public ReturnType(int high, int maxDistance) {
            this.high = high;
            this.maxDistance = maxDistance;
        }
    }

    public static ReturnType process(Node head) {
        if (head == null) {
            return new ReturnType(0, 0);
        }
        ReturnType leftReturnType = process(head.left);
        ReturnType rightReturnType = process(head.right);

        int includeMyMaxDistance = leftReturnType.high + 1 + rightReturnType.high;
        int maxDistance = Math.max(Math.max(leftReturnType.maxDistance, rightReturnType.maxDistance), includeMyMaxDistance);
        int high = Math.max(leftReturnType.high, rightReturnType.high) + 1;
        return new ReturnType(high, maxDistance);
    }

    public static void main(String[] args) {

    }
}
