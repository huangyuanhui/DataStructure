package com.hyh.datastructure.tree;

//二叉树
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "唐僧");
        HeroNode node1 = new HeroNode(2, "孙悟空");
        HeroNode node2 = new HeroNode(3, "猪八戒");
        HeroNode node3 = new HeroNode(4, "沙僧");
        HeroNode node4 = new HeroNode(5, "白龙马");

        binaryTree.head = root;
        root.setLeft(node1);
        root.setRight(node2);
        node2.setRight(node3);
        node2.setLeft(node4);

        //遍历
        /*binaryTree.prefixOrder();
        System.out.println();

        binaryTree.infixOrder();
        System.out.println();

        binaryTree.postfixOrder();*/

        //查找
        /*System.out.println("binaryTree.prefixSearch(5) = " + binaryTree.prefixSearch(5));
        System.out.println();

        System.out.println("binaryTree.infixSearch(5) = " + binaryTree.infixSearch(5));
        System.out.println();

        System.out.println("binaryTree.postfixSearch(5) = " + binaryTree.postfixSearch(5));*/

        //删除
        System.out.println("删除前：");
        binaryTree.prefixOrder();
        binaryTree.delete(2);
        System.out.println("删除后：");
        binaryTree.prefixOrder();
    }
}

//二叉树数据结构
class BinaryTree {
    public HeroNode head;

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