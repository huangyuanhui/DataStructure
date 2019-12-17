package com.hyh.datastructure.queue;

import java.util.Scanner;

public class ArrayQueueDemo2 {
    public static void main(String[] args) {
        ArrayCircleQueue queue = new ArrayCircleQueue(4);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        char key = ' ';
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    try {
                        queue.showQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                case 'a':
                    try {
                        System.out.print("输入加入队列的数据：");
                        int value=scanner.nextInt();
                        queue.addQueue(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.println("从队列中取出：" + res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.println("查看队列头数据：" + res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("Program exit.");
    }
}

//我们约定rear指向队尾后一个位置
class ArrayCircleQueue {
    private int front; //指向队列头
    private int rear;  //指向队列尾后面一个空间
    private int maxSize;
    private int[] array;

    public ArrayCircleQueue(int maxSize) {
        this.maxSize =maxSize + 1;
        array = new int[maxSize + 1];
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public void addQueue(int element) {
        if (isFull()) {
            System.out.println("队列已满。。。。");
            return;
        }
        array[rear] = element;
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空。。。。");
        }
        int element =  array[front];
        front = (front + 1) % maxSize;
        return element;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空。。。。");
            return;
        }
        //有效个数
        for (int i = 0; i < (rear - front + maxSize) % maxSize ; i++) {
            System.out.print("array[" + (front + i) % maxSize + "] = " + array[(front + i) % maxSize] + " ");
        }
        System.out.println();
    }

    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空。。。。");
        }
        return array[front];
    }
}
