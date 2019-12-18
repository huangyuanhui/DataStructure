package com.hyh.datastructure.stack;

import java.util.Stack;

//数组模拟栈
public class ArrayStackDemo {
    public static void main(String[] args) {
        /*ArrayStack stack = new ArrayStack(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.list();
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());*/


        ArrayStack3 stack = new ArrayStack3();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println("stack.getMin() = " + stack.getMin());
    }
}

//栈数据结构
class ArrayStack {
    private int top = -1;
    private int maxSize;
    private int[] stack;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    //压/入栈
    public void push(int element) {
        if (isFull()) {
            System.out.println("栈已满。。。。。");
            return;
        }
        stack[++top] = element;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空。。。。");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空。。。。");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }

    //获取栈顶数据
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空。。。。");
        }
        return stack[top];
    }
}


//栈数据结构 实现获取栈中最小元素 同时时间复杂度O(1)
class ArrayStack3 {
    private Stack<Integer> numStack;
    private Stack<Integer> minStack;

    public ArrayStack3() {
        numStack = new Stack<>();
        minStack = new Stack<>();
    }

    public boolean isEmpty() {
        return numStack.isEmpty();
    }

    //压/入栈
    public void push(int element) {
        numStack.push(element);
        if (minStack.isEmpty()) {
            minStack.push(element);
        } else {
            element = element > minStack.peek() ? minStack.peek() : element;
            minStack.push(element);
        }
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空。。。。");
        }
        minStack.pop();
        return numStack.pop();
    }

    //获取栈内最小的数
    public int getMin() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空。。。。");
        }
        return minStack.peek();
    }

}
