package com.hyh.datastructure.linkedlsit;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addByOrder(new HeroNode2(4, "韦德", "闪电侠"));
        doubleLinkedList.addByOrder(new HeroNode2(5, "哈登", "大胡子"));
        doubleLinkedList.addByOrder(new HeroNode2(1, "科比", "小飞侠"));
        doubleLinkedList.addByOrder(new HeroNode2(2, "詹姆斯", "小皇帝"));
        doubleLinkedList.addByOrder(new HeroNode2(2, "詹姆斯", "小皇帝"));
        doubleLinkedList.addByOrder(new HeroNode2(3, "杜兰特", "死神"));
        doubleLinkedList.list();
        System.out.println();

        doubleLinkedList.edit(new HeroNode2(1, "科比布莱恩特", "黑曼巴"));
        doubleLinkedList.list();
        System.out.println();

        doubleLinkedList.delete(5);
        doubleLinkedList.list();
    }



}

//双向链表数据结构
class DoubleLinkedList {
    public HeroNode2 head = new HeroNode2(0, null, null);

    //增
    public void add(HeroNode2 node) {
        HeroNode2 tempNode = head;
        while (true) {
            if (tempNode.next == null) {
                tempNode.next = node;
                node.pre = tempNode;
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

        HeroNode2 tempNode = head.next;
        boolean isExist = false;
        while (true) {
            if (tempNode == null) {
                break;
            }
            if (tempNode.no == no) {
                isExist = true;
                break;
            }
            tempNode = tempNode.next;
        }

        if (isExist) {
            tempNode.pre.next = tempNode.next;
            //判断是否是最后一个节点
            if (tempNode.next != null) {
                tempNode.next.pre = tempNode.pre;
            }
        }else {
            System.out.println("不存在该编号的节点。。。。");
        }
    }

    //该
    public void edit(HeroNode2 node) {
        HeroNode2 tempNode = head.next;
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
    public void addByOrder(HeroNode2 node) {
        if (head.next == null) {
            head.next = node;
            node.pre = head;
            return;
        }

        HeroNode2 tempNode = head;
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
        if (tempNode.next == null) {
            tempNode.next = node;
            node.pre = tempNode;
        }else {
            tempNode.next.pre = node;
            node.next = tempNode.next;
            node.pre = tempNode;
            tempNode.next = node;
        }
    }

    //查
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空。。。。");
            return;
        }
        HeroNode2 tempNode = head.next;
        while (true) {
            if (tempNode == null) {
                break;
            }
            System.out.println(tempNode);
            tempNode = tempNode.next;
        }
    }
}

//节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}