package com.hyh.datastructure.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
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

class ArrayQueue {
    public int front; //队列头 初始指向-1
    public int rear;  //队列尾 初始指向-1
    public int maxSize; //队列最大容量
    public int[] array; //数组

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
        front = -1;
        rear = -1;
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    //加数据
    public void  addQueue(int element) {
        if (isFull()) {
            System.out.println("队列已满。。。。");
            return;
        }
        array[++rear] = element;
    }

    //取数据
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空。。。。");
        }
        return array[++front];
    }

    //显示队列
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空。。。。");
            return;
        }
        for (int i = front + 1; i <= rear; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    //获取队列头
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空。。。。");
        }
        return array[front +1];
    }
}
