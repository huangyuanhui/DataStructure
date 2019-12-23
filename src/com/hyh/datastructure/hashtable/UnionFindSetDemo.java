package com.hyh.datastructure.hashtable;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

//并查集
public class UnionFindSetDemo {
    public static class Node {

    }

    public static class UnionFindSet {
        public HashMap<Node, Node> fatherMap;
        public HashMap<Node, Integer> sizeMap;

        public UnionFindSet(List<Node> nodes) {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (Node node : nodes) {
                //初始每个节点的父节点都是自己
                fatherMap.put(node, node);
                //初始每个节点自己是一个集合
                sizeMap.put(node, 1);
            }
        }

        //非递归（非递归版本往往借助栈结构，因为递归也是系统栈）
        private Node findHead(Node node) {
            Stack<Node> stack = new Stack<>();
            Node father = fatherMap.get(node);
            while (node != father) {
                stack.push(node);
                node = father;
                father = fatherMap.get(node);
            }

            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(), father);
            }
            return father;
        }

        public boolean isSameSet(Node a, Node b) {
            return findHead(a) == findHead(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aHead = findHead(a);
            Node bHead = findHead(b);
            if (aHead != bHead) {
                int aSize = sizeMap.get(aHead);
                int bSize = sizeMap.get(bHead);
                if (aSize <= bSize) {
                    fatherMap.put(aHead, bHead);
                    sizeMap.put(bHead, aSize + bSize);
                } else {
                    fatherMap.put(bHead, aHead);
                    sizeMap.put(aHead, aSize + bSize);
                }
            }

        }

    }
}
