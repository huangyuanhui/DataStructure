package com.hyh.datastructure.linkedlsit;

import java.util.Stack;

//单向链表
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.list();
        /*singleLinkedList.add(new HeroNode(1, "科比", "小飞侠"));
        singleLinkedList.add(new HeroNode(2, "詹姆斯", "小皇帝"));
        singleLinkedList.add(new HeroNode(3, "杜兰特", "死神"));
        singleLinkedList.add(new HeroNode(4, "韦德", "闪电侠"));
        singleLinkedList.add(new HeroNode(5, "哈登", "大胡子"));*/
        singleLinkedList.addByOrder(new HeroNode(4, "韦德", "闪电侠"));
        singleLinkedList.addByOrder(new HeroNode(5, "哈登", "大胡子"));
        singleLinkedList.addByOrder(new HeroNode(1, "科比", "小飞侠"));
        singleLinkedList.addByOrder(new HeroNode(2, "詹姆斯", "小皇帝"));
        singleLinkedList.addByOrder(new HeroNode(2, "詹姆斯", "小皇帝"));
        singleLinkedList.addByOrder(new HeroNode(3, "杜兰特", "死神"));

        singleLinkedList.list();

       // singleLinkedList.delete(5);

        //singleLinkedList.edit(new HeroNode(1, "科比布莱恩特", "黑曼巴"));
        //singleLinkedList.list();

        //System.out.println(getLength(singleLinkedList.head));

        //System.out.println(getHeroNode(singleLinkedList.head, 1));

        System.out.println();
        //reverseLinkedList(singleLinkedList.head);
        //singleLinkedList.list();

        reversePrint(singleLinkedList.head);
    }

    //链表 逆序打印
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            System.out.println("链表为空。。。。。。");
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode tempNode = head.next;
        while (true) {
            if (tempNode == null) {
                break;
            }
            stack.push(tempNode);
            tempNode = tempNode.next;
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    //反转链表 头插法
    public  static void reverseLinkedList(HeroNode head) {
        if (head.next == null) {
            System.out.println("链表为空。。。。。。");
            return;
        }

        HeroNode reverseHeadNode = new HeroNode(0, null, null);
        HeroNode tempNode = head.next;
        while (true) {
            if (tempNode == null) {
                break;
            }
            HeroNode temp = tempNode.next;
            tempNode.next = reverseHeadNode.next;
            reverseHeadNode.next = tempNode;
            tempNode = temp;
        }
        head.next = reverseHeadNode.next;
    }

    //求链表长度
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }

        HeroNode tempNode = head.next;
        int length = 0;
        while (true) {
            if (tempNode == null) {
                break;
            }
            length++;
            tempNode = tempNode.next;
        }
        return length;
    }

    //返回倒数第k个节点
    public static HeroNode getHeroNode(HeroNode head, int k) {
        if (head.next == null) {
            throw new RuntimeException("链表为空。。。。");
        }

        if (k <= 0) {
            throw new RuntimeException("长度不正确。。。。");
        }
        if (getLength(head) < k) {
            throw new RuntimeException("链表长度小于" + k);
        }
        HeroNode tempNode = head;
        int count = 0;
        while (true) {
            if (count >= (getLength(head) - k + 1)) {
                break;
            }
            tempNode = tempNode.next;
            count++;
        }
        return tempNode;
    }

}

class SingleLinkedList {
    public HeroNode head = new HeroNode(0, null, null);

    //增
    public void add(HeroNode node) {
        HeroNode tempNode = head;
        while (true) {
            if (tempNode.next == null) {
                tempNode.next = node;
                break;
            }
            tempNode = tempNode.next;
        }
    }

    //删
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空。。。。");
            return;
        }

        HeroNode tempNode = head;
        boolean isExist = false;
        while (true) {
            if (tempNode.next == null) {
                break;
            }
            if (tempNode.next.no == no) {
                isExist = true;
                break;
            }
            tempNode = tempNode.next;
        }

        if (isExist) {
            tempNode.next = tempNode.next.next;
        }else {
            System.out.println("不存在该编号的节点。。。。");
        }
    }

    //该
    public void edit(HeroNode node) {
        HeroNode tempNode = head.next;
        boolean isExist = false;
        while (true) {
            if (tempNode == null) {
                break;
            }
            if (tempNode.no == node.no) {
                isExist = true;
                break;
            }
            tempNode = tempNode.next;
        }
        if (isExist) {
            tempNode.name = node.name;
            tempNode.nickname = node.nickname;
        }else {
            System.out.println("不存在改编号的节点");
        }
    }

    //有序插入 因为是单向链表 所以我们要找的是要插入位置的前一个结点
    public void addByOrder(HeroNode node) {
        HeroNode tempNode = head;
        boolean isExist = false;
        while (true) {
            if (tempNode.next == null) {
                break;
            }
            if (tempNode.next.no > node.no) {
                break;
            }else if (tempNode.next.no == node.no) {
                isExist = true;
                break;
            }
            tempNode = tempNode.next;
        }
        if (isExist) {
            System.out.println("已存在相同编号的节点。。。。。");
            return;
        }
        node.next = tempNode.next;
        tempNode.next = node;
    }

    //查
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空。。。。");
            return;
        }
        HeroNode tempNode = head.next;
        while (true) {
            if (tempNode == null) {
                break;
            }
            System.out.println(tempNode);
            tempNode = tempNode.next;
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}