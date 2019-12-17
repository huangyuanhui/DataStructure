package com.hyh.datastructure.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree tree = new ArrBinaryTree(arr);
        tree.preOrder();
        System.out.println();
        tree.inOrder();
        System.out.println();
        tree.postOrder();
    }


}

class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //前序遍历出数组
    public void preOrder(int index) {
        if (arr == null || arr.length == 0 || index >= arr.length || index < 0) {
            return;
        }
        System.out.print(arr[index] + " ");
        //遍历左子树
        preOrder(2 * index + 1);
        //遍历右子树
        preOrder(2 * index + 2);
    }

    //重载preOrder 显得简洁
    public void preOrder() {
        preOrder(0);
    }

    //前序遍历出数组
    public void inOrder(int index) {
        if (arr == null || arr.length == 0 || index >= arr.length || index < 0) {
            return;
        }
        //遍历左子树
        inOrder(2 * index + 1);
        System.out.print(arr[index] + " ");
        //遍历右子树
        inOrder(2 * index + 2);
    }

    //重载preOrder 显得简洁
    public void inOrder() {
        inOrder(0);
    }

    //前序遍历出数组
    public void postOrder(int index) {
        if (arr == null || arr.length == 0 || index >= arr.length || index < 0) {
            return;
        }
        //遍历左子树
        postOrder(2 * index + 1);
        //遍历右子树
        postOrder(2 * index + 2);
        System.out.print(arr[index] + " ");
    }

    //重载preOrder 显得简洁
    public void postOrder() {
        postOrder(0);
    }


}