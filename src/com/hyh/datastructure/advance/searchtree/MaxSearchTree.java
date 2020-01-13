package com.hyh.datastructure.advance.searchtree;

/**
 * 求一颗树的最大搜索二叉子树：
 * <p>
 * 第一步，列出可能性（最难部分）
 * 可能性1、可能来自左子树上的某课子树
 * 可能性2、可能来自右子树上的某课子树
 * 可能性3、整颗都是（左右子树都是搜索二叉树并且左子树最大小于该节点，右子树最小大于该节点）
 * <p>
 * 第二步，需要收集的信息：
 * 1、左树最大搜索子树大小
 * 2、右树最大搜索子树大小
 * 3、左树最大二叉搜索子树的头部（通过查看这个头部是否等于节点的左孩子，来判断整个左子树是否都是二叉搜索树）
 * 4、右树最大二叉搜索子树的头部
 * 5、左树最大值
 * 6、右树最小值
 * <p>
 * 第三步化简为一个信息体：
 * 1、左/右子树最大搜索子树大小
 * 2、左/右子树最大搜索子树头节点
 * 3、左子树max
 * 4、右子树min
 * 也就是
 * 不管左树还是右树都存储
 * 1、最大搜索子树大小
 * 2、最大搜索子树的头部
 * 3、这棵树上的最大值和最小值
 */
public class MaxSearchTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node biggestSubBST(Node head) {
        int[] record = new int[3]; // 0->size, 1->min, 2->max
        return posOrder(head, record);
    }

    /**
     * 消息体结构
     * 最大搜索子树大小
     * 最大搜索子树的头部
     * 这棵树上的最大值
     * 这棵树上的最小值
     */
    public static class ReturnType {
        public int size;
        public Node head;
        public int min;
        public int max;

        public ReturnType(int size, Node head, int min, int max) {
            this.size = size;
            this.head = head;
            this.min = min;
            this.max = max;
        }
    }

    public static ReturnType process(Node head) {
        if (head == null) {
            return new ReturnType(0, null, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        Node left = head.left;
        ReturnType leftSubTreeInfo = process(left);
        Node right = head.right;
        ReturnType rightSubTreeInfo = process(right);

        int includeMySelf = 0;
        if (leftSubTreeInfo.head == left && rightSubTreeInfo.head == right
                && leftSubTreeInfo.max < head.value && rightSubTreeInfo.min > head.value) {
            includeMySelf = leftSubTreeInfo.size + 1 + rightSubTreeInfo.size;
        }

        int leftSize = leftSubTreeInfo.size;
        int rightSize = rightSubTreeInfo.size;
        int maxSize = Math.max(Math.max(leftSize, rightSize), includeMySelf);

        Node headNode = leftSize > rightSize ? leftSubTreeInfo.head : rightSubTreeInfo.head;
        if (maxSize == includeMySelf) {
            headNode = head;
        }

        return new ReturnType(maxSize, headNode,
                Math.min(Math.min(leftSubTreeInfo.min, rightSubTreeInfo.min), head.value),
                Math.max(Math.max(leftSubTreeInfo.max, rightSubTreeInfo.max), head.value));
    }


    public static ReturnType process1(Node head) {
        if (head == null) {
            //设置系统最大最小为了不干扰判断最大最小的决策
            return new ReturnType(0, null, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        Node left = head.left;
        ReturnType leftSubTressInfo = process1(left);//当成一个黑盒
        Node right = head.right;
        ReturnType rightSubTressInfo = process1(right);

        //可能性3
        int includeItSelf = 0;
        if (leftSubTressInfo.head == left
                && rightSubTressInfo.head == right
                && head.value > leftSubTressInfo.max
                && head.value < rightSubTressInfo.min
        ) {
            includeItSelf = leftSubTressInfo.size + 1 + rightSubTressInfo.size;
        }
        int p1 = leftSubTressInfo.size;
        int p2 = rightSubTressInfo.size;
        //解黑盒的过程
        int maxSize = Math.max(Math.max(p1, p2), includeItSelf);

        Node maxHead = p1 > p2 ? leftSubTressInfo.head : rightSubTressInfo.head;
        if (maxSize == includeItSelf) {
            maxHead = head;
        }

        return new ReturnType(maxSize,
                maxHead,
                Math.min(Math.min(leftSubTressInfo.min, rightSubTressInfo.min), head.value),
                Math.max(Math.max(leftSubTressInfo.max, rightSubTressInfo.max), head.value));
    }

    //数组实现版本
    public static Node posOrder(Node head, int[] record) {
        if (head == null) {
            record[0] = 0;
            record[1] = Integer.MAX_VALUE;
            record[2] = Integer.MIN_VALUE;
            return null;
        }
        int value = head.value;
        Node left = head.left;
        Node right = head.right;
        Node lBST = posOrder(left, record);
        int lSize = record[0];
        int lMin = record[1];
        int lMax = record[2];
        Node rBST = posOrder(right, record);
        int rSize = record[0];
        int rMin = record[1];
        int rMax = record[2];
        record[1] = Math.min(rMin, Math.min(lMin, value)); // lmin, value, rmin -> min
        record[2] = Math.max(lMax, Math.max(rMax, value)); // lmax, value, rmax -> max
        if (left == lBST && right == rBST && lMax < value && value < rMin) {
            record[0] = lSize + rSize + 1;
            return head;
        }
        record[0] = Math.max(lSize, rSize);
        return lSize > rSize ? lBST : rBST;
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {

        Node head = new Node(6);
        head.left = new Node(1);
        head.left.left = new Node(0);
        head.left.right = new Node(3);
        head.right = new Node(12);
        head.right.left = new Node(10);
        head.right.left.left = new Node(4);
        head.right.left.left.left = new Node(2);
        head.right.left.left.right = new Node(5);
        head.right.left.right = new Node(14);
        head.right.left.right.left = new Node(11);
        head.right.left.right.right = new Node(15);
        head.right.right = new Node(13);
        head.right.right.left = new Node(20);
        head.right.right.right = new Node(16);

        printTree(head);
        Node bst = biggestSubBST(head);
        printTree(bst);

    }
}
