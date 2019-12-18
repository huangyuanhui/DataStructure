package com.hyh.datastructure.queue;

import java.util.Stack;

//用栈实现队列结构
public class StackQueueDemo {
    public static void main(String[] args) {
        StackQueue queue = new StackQueue();
        queue.push(1);
        queue.push(2);
        System.out.println("queue.pop() = " + queue.pop());
        queue.push(3);
        System.out.println("queue.pop() = " + queue.pop());
        queue.push(4);
        System.out.println("queue.pop() = " + queue.pop());
        System.out.println("queue.pop() = " + queue.pop());
        System.out.println("queue.pop() = " + queue.pop());

    }
}


class StackQueue {
    private Stack<Integer> pushStack; //加数据永远加在pushStack
    private Stack<Integer> popStack;  //取数据永远在popStack中取

    public StackQueue() {
        pushStack = new Stack<>();
        popStack = new Stack<>();
    }

    public void push(int element) {
        pushStack.push(element);
    }

    public int pop() {
        if (pushStack.isEmpty() && popStack.isEmpty()) {
            throw new RuntimeException("队列为空。。。。");
        }
        if (popStack.isEmpty()) {
            //push栈全部倒进pop栈
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
        return popStack.pop();
    }

    private int peek() {
        if (pushStack.isEmpty() && popStack.isEmpty()) {
            throw new RuntimeException("队列为空。。。。");
        }
        if (popStack.isEmpty()) {
            //push栈全部倒进pop栈
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
        return popStack.peek();
    }
/*
    private void dao() {
        //pop栈有数据 push栈不能向pop栈倒数据
        if (!popStack.isEmpty()) {
            return;
        }

        //如果要倒数据 push栈一定要将栈中数据全部倒完
        while (!pushStack.isEmpty()) {
            popStack.push(pushStack.pop());
        }
    }*/
}
