package com.hyh.datastructure.advance.morris;

/**
 * 关于二叉树先序、中序、后序遍历的递归和非递归版本中，这6种遍历算法的时间复杂度都需要O(H)（其中H为树高）的
 * 额外空间复杂度，因为二叉树遍历过程中只能向下查找孩子节点而无法回溯父结点，因此这些算法借助栈来保存要回溯
 * 的父节点（递归的实质是系统帮我们压栈），并且栈要保证至少能容纳下H个元素（比如遍历到叶子结点时回溯父节点，
 * 要保证其所有父节点在栈中）。而morris遍历则能做到时间复杂度仍为O(N)的情况下额外空间复杂度只需O(1)。
 * <p>
 * 遍历规则
 * 首先在介绍morris遍历之前，我们先把先序、中序、后序定义的规则抛之脑后，比如先序遍历在拿到一棵树之后先遍历
 * 头结点然后是左子树最后是右子树，并且在遍历过程中对于子树的遍历仍是这样。
 * 忘掉这些遍历规则之后，我们来看一下morris遍历定义的标准：
 * <p>
 * 1. 定义一个遍历指针cur，该指针首先指向头结点
 * 2. 判断cur的左子树是否存在
 * 2.1  如果cur的左孩子为空，说明cur的左子树不存在，那么cur右移来到cur.right
 * 2.2  如果cur的左孩子不为空，说明cur的左子树存在，找出该左子树的最右结点，记为mostRight
 * 2.3  如果mostRight的右孩子为空，那就让其指向cur（mostRight.right=cur），并左移cur（cur=cur.left）
 * 2.4  如果mostRight的右孩子不空，那么让cur右移（cur=cur.right），并将mostRight的右孩子置空
 * 3. 经过步骤2之后，如果cur不为空，那么继续对cur进行步骤2，否则遍历结束。
 */


/**
 * 遍历本身能解决很多问题 而morris遍历是一种特别牛B的遍历
 */
public class MorrisDemo {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        //前序
        /*morrisPre(root);
        recursErgodic(root);*/

        //中序
        /*morrisIn(root);
        recursErgodic(root);*/

        //后序
        morrisPos(root);
        recursErgodic(root);
    }

    /**
     * 使用morris遍历得到二叉树的后序序列就没那么容易了，因为对于树种的非叶结点，morris遍历最多会经过它两次，
     * 而我们后序遍历实在第三次来到该结点时打印该结点的。因此要想得到后序序列，仅仅改变在morris遍历时打印结
     * 点的时机是无法做到的。
     *
     * 但其实，整颗树是可以被右边界分解掉的 在morris遍历过程中，如果在每次遇到第二次经过的结点时，将该结点的左子树的右边界上的结点从
     * 下到上打印，最后再将整个树的右边界从下到上打印，最终就是这个数的后序序列
     * 无非就是在morris遍历中在第二次经过的结点的时机执行一下打印操作。而从下到上打印一棵树的右边界，可以
     * 将该右边界上的结点看做以right指针为后继指针的链表，将其反转reverse然后打印，最后恢复成原始结构即可
     * @param head
     */
    private static void morrisPos(Node head) {
        if (head == null) {
            return;
        }
        Node curr = head;
        Node mostRight = null;
        while (curr != null) {
            mostRight = curr.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != curr) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = curr;
                    curr = curr.left;
                    continue;
                }else {
                    mostRight.right = null;
                    printRightEdge(curr.left);
                }
            }
            curr = curr.right;
        }
        //单独打印整颗树的右边界
        printRightEdge(head);
        System.out.println();
    }

    //逆序打印一颗树的右边界 额外空间复杂度O(1)
    private static void printRightEdge(Node root) {
        if (root == null) {
            return;
        }
        //reverse the right edge
        Node cur = root;
        Node pre = null;
        while (cur != null) {
            Node next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        //print
        cur = pre;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        //recover
        cur = pre;
        pre = null;
        while (cur != null) {
            Node next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
    }

    //morris中序遍历
    private static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node curr = head;
        Node mostRight = null;
        while (curr != null) {
            mostRight = curr.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != curr) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = curr;
                    curr = curr.left;
                    continue;
                }else {
                    mostRight.right = null;
                }
            }
            //当这个节点要往右窜了 打印（包含了这个节点没有左子树 以及mostRight的节点right指向当前curr的情况）
            System.out.print(curr.value + " ");
            curr = curr.right;
        }
        System.out.println();
    }


    //morris前序遍历
    private static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node curr = head;
        Node mostRight = null;
        while (curr != null) {
            mostRight = curr.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != curr) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    //这个节点左子树最有节点的右孩子指向空 则第一次来到 打印
                    System.out.print(curr.value + " ");
                    mostRight.right = curr;
                    curr = curr.left;
                    continue;
                }else {
                    mostRight.right = null;
                }
            }else {
                //一个当前节点没有左子树的时候 只来到一次 打印
                System.out.print(curr.value + " ");
            }
            curr = curr.right;
        }
        System.out.println();
    }


    //morris算法流程
    //morris遍历的本质是这样一种遍历：
    // 如果一个节点有左子树 那么会来到这个节点两次 如果一个节点没有左子树 那么只会来到这个节点一次
    //并且第二次来到这个节点的时候 这个节点左子树都已经遍历完了
    //那么如何标记是第几次来到这个节点呢？是通过根据这个节点左子树最有节点的右孩子指向谁来确定
    //这个节点左子树最有节点的右孩子指向空 则第一次来到：
    //这个节点左子树最有节点的右孩子指向自己 则第二次来到
    private static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node curr = head;
        Node mostRight = null;
        while (curr != null) {
            mostRight = curr.left;
            if (mostRight != null) {
                //窜到左子树最右的节点
                while (mostRight.right != curr && mostRight.right != null) {
                    mostRight = mostRight.right;
                }
                //也就只有两种情况才会跳出while 要么mostRight.right = curr 要么mostRight.right = null
                if (mostRight.right == null) {
                    //mostRight的右孩子为空，那就让mostRight.right指向cur，并左移cur（cur=cur.left）
                    mostRight.right = curr;
                    curr = curr.left;
                    continue;
                }else {
                    //mostRight的节点right指向当前curr，那么将mostRight的右孩子置空，并让cur右移（cur=cur.right），
                    mostRight.right = null;
                }
            }
            //cur的左孩子为空，cur右移来到cur.right
            curr = curr.right;
        }
    }


    public static class Node {
        public Node left;
        public Node right;
        public int value;

        public Node(int value) {
            this.value = value;
        }
    }

    //前序 中序 后序
    private static void recursErgodic(Node head) {
        if (head == null) {
            return;
        }
        //System.out.print(head.value + " ");
        recursErgodic(head.left);
        //System.out.print(head.value + " ");
        recursErgodic(head.right);
        System.out.print(head.value + " ");
    }
}
