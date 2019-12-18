package com.hyh.datastructure.queue;

import java.util.Scanner;

public class ArrayQueueDemo2 {
    public static void main(String[] args) {
        /*ArrayCircleQueue queue = new ArrayCircleQueue(4);
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
        System.out.println("Program exit.");*/


        ArrayCircleQueue2 queue = new ArrayCircleQueue2(4);
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
                        queue.list();
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
                        queue.push(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        int res = queue.poll();
                        System.out.println("从队列中取出：" + res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.peek();
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


//我们约定rear指向队尾后一个位置 (此处实现是数组没有空一个位置的)
class ArrayCircleQueue2 {
    private int front; //指向队列头 初始为0
    private int rear;  //指向队列尾 初始为0
    private int size; //初始为0 记录队列有效元素的个数 实现对front与rear解耦
    private int[] array;

    public ArrayCircleQueue2(int arrSize) {
        array = new int[arrSize];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    //添加数据进队列
    public void push(int element) {
        if (isFull()) {
            System.out.println("队列已满。。。。");
            return;
        }
        array[rear++] = element;
        rear = rear == array.length ? 0 : rear;
        //有效个数加一
        size++;
    }

    //数据出循环队列
    public int poll() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空。。。。");
        }
        int temp = front;
        front = front == array.length - 1 ? 0 : front + 1;
        size--;
        return array[temp];
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("队列为空。。。。");
            return;
        }
        //有效个数
        int step = rear != front ? (rear - front + array.length) % array.length : array.length;
        for (int i = 0; i < step; i++) {
            System.out.print("array[" + (front + i) % array.length + "] = " + array[(front + i) % array.length] + " ");
        }
        System.out.println();
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空。。。。");
        }
        return array[front];
    }
}
