package com.hyh.datastructure.tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//霍夫曼树
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3 ,29, 6, 1};
        Node root = createHuffmanTree(arr);
        System.out.println("createHuffmanTree(arr) = " + root);
        preOrder(root);
    }

    private static void preOrder(Node root) {
        if (root == null) {
            System.out.println("霍夫曼树为空树");
            return;
        }
        root.preOrder();
    }
    //创建霍夫曼树
    private static Node createHuffmanTree(int[] arr) {
        List<Node> nodeList = new ArrayList<>();
        for (int value : arr) {
            nodeList.add(new Node(value));
        }

        while (nodeList.size() > 1) {
            Collections.sort(nodeList);
            Node leftNode = nodeList.get(0);
            Node rightNode = nodeList.get(1);
            Node parentNode = new Node(leftNode.value + rightNode.value);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            nodeList.add(parentNode);
        }
        return nodeList.get(0);
    }
}

class Node implements Comparable<Node>{
    public int value;
    public Node left;
    public Node right;

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大
        return this.value - o.value;
    }
}

//升序比较器
class AscComparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        //升序
        return o1.value - o2.value;
    }
}

//降序比较器
class DescComparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        //降序
        return o2.value - o1.value;
    }
}