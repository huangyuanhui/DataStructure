package com.hyh.datastructure.stack;

public class LinkedListStackDemo {
    public static void main(String[] args) {

    }
}

class LinkedListStack {
    private int maxSize;
    private StackNode head = new StackNode();

    public boolean isFull() {
        return true;
    }

    public boolean isEmpty() {
        return head.getNext() == null;
    }
    public void push(int element) {

    }

    public int pop() {
        return 0;
    }

    public void list() {

    }
}

class StackNode {
    private int number;
    private StackNode next;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public StackNode getNext() {
        return next;
    }

    public void setNext(StackNode next) {
        this.next = next;
    }
}
