package com.hyh.datastructure.tree.zuoshen;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//判断一颗树是否是搜索二叉树：对于任何一个节点 他的左子树都比他小 他的右子树都比他大
//判断：二叉树的中序遍历序列是升序的 就是平衡二叉树（注意：搜索二叉树通常是不出现重复节点的 因为有重复节点 可
// 以压缩在一起）
public class Code_07_IsBSTAndCBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	//中序遍历实现判断一棵树是否是搜索二叉树
	public static boolean isBalanceSearchTree(Node head) {
		if (head == null) {
			return true;
		}
		Stack<Node> stack = new Stack<>();
		int preValue = 0;
		boolean isBalance = true;
		while (!stack.isEmpty() || head != null) {
			if (head != null) {
				stack.push(head);
				preValue = head.value;
				head = head.left;
			}else {
				head = stack.pop();
				if (head.value < preValue) {
					isBalance = false;
					break;
				}
				preValue = head.value;
				head = head.right;
			}
		}
		return isBalance;
	}

	public static boolean isBST(Node head) {
		if (head == null) {
			return true;
		}
		boolean res = true;
		Node pre = null;
		Node cur1 = head;
		Node cur2 = null;
		while (cur1 != null) {
			cur2 = cur1.left;
			if (cur2 != null) {
				while (cur2.right != null && cur2.right != cur1) {
					cur2 = cur2.right;
				}
				if (cur2.right == null) {
					cur2.right = cur1;
					cur1 = cur1.left;
					continue;
				} else {
					cur2.right = null;
				}
			}
			if (pre != null && pre.value > cur1.value) {
				res = false;
			}
			pre = cur1;
			cur1 = cur1.right;
		}
		return res;
	}

	public static boolean isCBT(Node head) {
		if (head == null) {
			return true;
		}
		Queue<Node> queue = new LinkedList<Node>();
		boolean leaf = false;
		Node l = null;
		Node r = null;
		queue.offer(head);
		while (!queue.isEmpty()) {
			head = queue.poll();
			l = head.left;
			r = head.right;
			if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
				return false;
			}
			if (l != null) {
				queue.offer(l);
			}
			if (r != null) {
				queue.offer(r);
			} else {
				leaf = true;
			}
		}
		return true;
	}

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		Node head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(7);

		printTree(head);
		System.out.println(isBST(head));
		System.out.println(isCBT(head));
		System.out.println("isBalanceSearchTree(head) = " + isBalanceSearchTree(head));

	}
}