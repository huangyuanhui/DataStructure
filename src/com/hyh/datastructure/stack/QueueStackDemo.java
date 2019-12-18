package com.hyh.datastructure.stack;

import java.util.LinkedList;
import java.util.Queue;

//队列实现栈结构
public class QueueStackDemo {
    public static void main(String[] args) {
        QueueStack stack = new QueueStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.pop() = " + stack.pop());
    }
}

//队列实现栈结构
class QueueStack {
    private Queue<Integer> data;  //数据栈
    private Queue<Integer> help; //辅助栈

    public QueueStack() {
        data = new LinkedList<>();
        help = new LinkedList<>();
    }

    public  int peek() {
        if (data.isEmpty()) {
            throw new RuntimeException("栈为空。。。。");
        }
        while (data.size() > 1) {
            help.add(data.poll());
        }
        int temp = data.poll();
        //进入队列仅仅
        help.add(temp);
        //改变引用
        swap();
        return temp;
    }
    //入栈
    public void push(int element) {
        data.add(element);
    }

    //出栈
    public int pop() {
        if (data.isEmpty()) {
            throw new RuntimeException("栈为空。。。。");
        }
        while (data.size() > 1) {
            help.add(data.poll());
        }
        int temp = data.poll();
        //改变引用
        swap();
        return temp;
    }

    private void swap() {
        Queue<Integer> temp = data;
        data = help;
        help = temp;
    }
}
