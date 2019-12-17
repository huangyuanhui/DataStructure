package com.hyh.datastructure.tree.threaded;

//线索化二叉树
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "大毛");
        HeroNode node2 = new HeroNode(3, "二毛");
        HeroNode node3 = new HeroNode(6, "三毛");
        HeroNode node4 = new HeroNode(8, "四毛");
        HeroNode node5 = new HeroNode(10, "五毛");
        HeroNode node6 = new HeroNode(14, "六毛");
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.head = root;

        //中序遍历
        threadedBinaryTree.infixOrder();

        //中序线索化
        threadedBinaryTree.threadedNodes();

        System.out.println();
        System.out.println("node5.getLeft() = " + node5.getLeft());
        System.out.println("node5.getRight() = " + node5.getRight());
        System.out.println();

        threadedBinaryTree.threadedList();
    }
}

//线索化二叉树数据结构（实现了线索化功能的二叉树）
class ThreadedBinaryTree {
    public HeroNode head;

    //用来记录线索化二叉树时每一个节点的前驱节点 初始为null
    public HeroNode pre;

    public void threadedNodes() {
        threadedNodes(head);
    }

    //遍历线索化二叉树（中序）
    public void threadedList() {
        if (head == null) {
            return;
        }
        HeroNode temp = head;
        while (temp != null) {
            while (temp.getLeftType() == 0) {
                temp = temp.getLeft();
            }
            System.out.println(temp);
            //该节点有后继节点
            while (temp.getRightType() == 1) {
                temp = temp.getRight();
                System.out.println(temp);
            }
            temp = temp.getRight();
        }
    }

    //对二叉树进行线索化(中序线索二叉树)
    public void threadedNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        //线索化左子树
        if (node.getLeft() != null) {
            threadedNodes(node.getLeft());
        }

        //线索化代码
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理一个节点后 让当前节点是下一个节点的前驱节点
        pre = node;

        //线索化右子树
        if (node.getRight() != null) {
            threadedNodes(node.getRight());
        }
    }

    //前序遍历
    public void prefixOrder() {
        if (head == null) {
            System.out.println("树为空。。。。");
            return;
        }
        head.prefixOrder();
    }

    //中序遍历
    public void infixOrder() {
        if (head == null) {
            System.out.println("树为空。。。。");
            return;
        }
        head.infixOrder();
    }

    //后序遍历
    public void postfixOrder() {
        if (head == null) {
            System.out.println("树为空。。。。");
            return;
        }
        head.postfixOrder();
    }

    //前序查找
    public HeroNode prefixSearch(int no) {
        if (head == null) {
            return null;
        }
        return head.prefixSearch(no);
    }

    //中序查找
    public HeroNode infixSearch(int no) {
        if (head == null) {
            return null;
        }
        return head.infixSearch(no);
    }

    //后序查找
    public HeroNode postfixSearch(int no) {
        if (head == null) {
            return null;
        }
        return head.postfixSearch(no);
    }

    //删除节点
    public void delete(int no) {
        //空树
        if (head == null) {
            System.out.println("树为空。。。。。");
            return;
        }
        //根节点就是要删除的节点
        if (head.getNo() == no) {
            head.setLeft(null);
            head.setRight(null);
        }
        head.delete(no);
    }
}

//节点类
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;  //左节点默认为null
    private HeroNode right;  //右节点默认为null

    //left节点的类型 : 值为0表示左子树 1表示前驱节点 默认为0
    private int leftType;
    //right节点的类型 ：值为0表示右子树 1表示后继节点 默认为0
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    //前序查找
    public  HeroNode prefixSearch(int no) {
        System.out.println("前序查找比较中。。。。。。。。");
        if (this.no == no) {
            return this;
        }
        HeroNode temp = null;
        if (this.left != null) {
            temp = this.left.prefixSearch(no);
        }
        //左子树找到 返回
        if (temp != null) {
            return temp;
        }

        if (this.right != null) {
            temp = this.right.prefixSearch(no);
        }
        return temp;
    }

    //中序查找
    public HeroNode infixSearch(int no) {
        HeroNode temp = null;
        if (this.left != null) {
            temp = this.left.infixSearch(no);
        }
        //左子树找到 返回
        if (temp != null) {
            return temp;
        }

        System.out.println("中序查找比较中。。。。。。。。");
        if (this.no == no) {
            return this;
        }

        if (this.right != null) {
            temp = this.right.infixSearch(no);
        }
        return temp;
    }

    //后序查找
    public HeroNode postfixSearch(int no) {
        HeroNode temp = null;
        if (this.left != null) {
            temp = this.left.postfixSearch(no);
        }
        //左子树找到 返回
        if (temp != null) {
            return temp;
        }

        if (this.right != null) {
            temp = this.right.postfixSearch(no);
        }
        //右子树找到 返回
        if (temp != null) {
            return temp;
        }

        System.out.println("后序查找比较中。。。。。。。。");
        if (this.no == no) {
            return this;
        }
        return temp;
    }


    //前序遍历
    public void prefixOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.prefixOrder();
        }
        if (this.right != null) {
            this.right.prefixOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postfixOrder() {
        if (this.left != null) {
            this.left.postfixOrder();
        }
        if (this.right != null) {
            this.right.postfixOrder();
        }
        System.out.println(this);
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //删除节点
    public void delete(int no) {
        //判断当前节点左子节点是否是要删除的节点 是则置空并return
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        //判断当前节点右子节点是否是要删除的节点 是则置空并return
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        //左子树递归删除
        if (this.left != null) {
            this.left.delete(no);
        }

        //右子树递归删除
        if (this.right != null) {
            this.right.delete(no);
        }
    }
}