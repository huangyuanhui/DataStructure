package com.hyh.datastructure.linkedlsit;
//约瑟夫问题
public class JosePhu {
    public static void main(String[] args) {
        SingleCircleLinkedList circleLinkedList = new SingleCircleLinkedList();
        circleLinkedList.addBoyNode(125);
        circleLinkedList.showBoys();
        circleLinkedList.countBoyNode(10, 20, 125);
    }
}

//单项环形链表
class SingleCircleLinkedList{
    //指向头节点的指针 不能变
    private BoyNode first;

    public BoyNode getFirst() {
        return first;
    }

    /**
     * 小孩出圈
     * @param startNo 从那个小孩开始
     * @param countNum 隔多少
     * @param nums 小孩数
     */
    public void countBoyNode(int startNo, int countNum, int nums) {
        if (first == null) {
            System.out.println("没有任何小孩。。。。");
            return;
        }
        if (startNo < 1 || startNo > nums) {
            System.out.println("数据输入有误。。。。");
            return;
        }

        BoyNode helper = first;
        //定位helper到first前一个
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }

        //first helper定位到开始位置
        for (int i = 1; i < startNo; i++) {
            helper = helper.getNext();
            first = first.getNext();
        }

        int i = 1;
        while (true) {
            if (helper == first) {
                System.out.println("最后出圈的小孩 : " + helper);
                break;
            }
            if (i % countNum == 0) {
                System.out.println(first);
                first = first.getNext();
                helper.setNext(first);
                i = 1;
            }
            first = first.getNext();
            helper = helper.getNext();
            i++;
        }

    }

    //添加小孩入圈
    public void addBoyNode(int nums) {
        if (nums < 1) {
            System.out.println("必须要有小孩。。。。");
            return;
        }

        BoyNode currBoy = null;
        for (int i = 1; i <= nums; i++) {
            BoyNode boyNode = new BoyNode(i);
            if (i == 1) {
                first = boyNode;
                first.setNext(first);
                currBoy = first;
            }else {
                currBoy.setNext(boyNode);
                boyNode.setNext(first);
                currBoy = boyNode;
            }
        }
    }

    //遍历小孩
    public void showBoys() {
        if (first == null) {
            System.out.println("没有任何小孩。。。。");
            return;
        }
        BoyNode temp = first;
        while (true) {
            System.out.println("小孩的编号是：" + temp.getNo());
            if (temp.getNext() == first) {
                break;
            }
            temp = temp.getNext();
        }
    }
}

class BoyNode{
    private Integer no;
    private BoyNode next;

    public BoyNode(Integer no) {
        this.no = no;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public BoyNode getNext() {
        return next;
    }

    public void setNext(BoyNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "BoyNode{" +
                "no=" + no +
                '}';
    }
}

