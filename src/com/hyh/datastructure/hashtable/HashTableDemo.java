package com.hyh.datastructure.hashtable;

import java.util.Scanner;

//数据结构之哈希表
public class HashTableDemo {
    public static void main(String[] args) {
        EmpHashTable hashTab = new EmpHashTable(5);
        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    EmpNode emp = new EmpNode(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    EmpNode empNode = hashTab.findEmpById(id);
                    System.out.println("find的结果为: " + empNode);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//哈希表数据结构（数组 + 链表实现）
class EmpHashTable {
    private EmpLinkedList[] empLinkedListArr;
    private int maxSize;

    public EmpHashTable(int maxSize) {
        this.maxSize = maxSize;
        this.empLinkedListArr = new EmpLinkedList[maxSize];
        //初始化数组
        for (int i = 0; i < maxSize; i++) {
            EmpLinkedList empLinkedList = new EmpLinkedList();
            this.empLinkedListArr[i] = empLinkedList;
        }
    }

    //向哈希表中添加数据
    public void add(EmpNode empNode) {
        empLinkedListArr[hash(empNode.id)].add(empNode);
    }

    //遍历哈希表
    public void list() {
        for (int i = 0; i < maxSize; i++) {
            empLinkedListArr[i].list(i);
        }
    }

    //散列函数（使用简单的取模法）
    private int hash(int id) {
        return id % maxSize;
    }

    //查找方法
    public EmpNode findEmpById(int id) {
        return empLinkedListArr[hash(id)].findEmpById(id);
    }
}

//员工链表数据结构
class EmpLinkedList {
    //头节点 指向第一个数据
    public EmpNode head;

    public void add(EmpNode node) {
        if (head == null) {
            head = node;
            return;
        }
        EmpNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    //遍历链表
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) + "条链表为空。。。。");
            System.out.println();
            return;
        }
        EmpNode temp = head;
        System.out.println("第" + (no + 1) + "条链表信息为：");
        while (temp != null) {
            System.out.println("temp = " + temp);
            temp = temp.next;
        }
        System.out.println();
    }

    public EmpNode findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空。。。。。");
            return null;
        }
        EmpNode temp = head;
        while (temp != null) {
            if (temp.id == id) {
                break;
            }
            temp = temp.next;
        }
        return temp;
    }
}

//员工节点
class EmpNode {
    public int id;
    public String name;
    //指向下一个节点
    public EmpNode next;

    public EmpNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "EmpNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
