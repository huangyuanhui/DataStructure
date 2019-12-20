package com.hyh.datastructure.tree.zuoshen;

//平衡二叉树：对于一个树的任意一个节点 其左子树和右子树的高度差不超过1（平衡性用来解决效率问题的）
//对于树的一个套路：递归函数很好用 因为使用递归 会三次来到这个节点
//那么我们可以在第一次来到这个节点的时候收集一些信息 第二次来到这个节点的时候
//又收集一些信息 第三次来到这个节点的时候收集一些信息 如Code_04中的递归方式
//序列化一个树的方法就有体现
public class Code_06_IsBalancedTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static class ReturnRes {
		public int height;
		public boolean isBalance;

		public ReturnRes(int height, boolean isBalance) {
			this.height = height;
			this.isBalance = isBalance;
		}
	}

	public static ReturnRes isBalanceTree(Node head) {
		if (head == null) {
			//空节点是平衡的 高度为0
			return new ReturnRes(0, true);
		}
		//左子树的结果
		ReturnRes leftRes = isBalanceTree(head.left);
		if (!leftRes.isBalance) {
			return new ReturnRes(0, false);
		}
		//右子树的结果
		ReturnRes rightRes = isBalanceTree(head.right);
		if (!rightRes.isBalance) {
			return new ReturnRes(0, false);
		}
		//左右子树的高度差
		if (Math.abs(leftRes.height - rightRes.height) > 1) {
			return new ReturnRes(0, false);
		}
		//是平衡树
		return new ReturnRes(Math.abs(leftRes.height - rightRes.height) + 1, true);
	}

	public static boolean isBalance(Node head) {
		boolean[] res = new boolean[1];
		res[0] = true;
		getHeight(head, 1, res);
		return res[0];
	}

	//左子树是否平衡 右子树是否平衡 左子树与右子树都平衡时 判断左右子树的高度差
	public static int getHeight(Node head, int level, boolean[] res) {
		if (head == null) {
			return level;
		}
		int lH = getHeight(head.left, level + 1, res);
		if (!res[0]) {
			return level;
		}
		int rH = getHeight(head.right, level + 1, res);
		if (!res[0]) {
			return level;
		}
		if (Math.abs(lH - rH) > 1) {
			res[0] = false;
		}
		return Math.max(lH, rH);
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);
		head.right.left.left = new Node(8);

		//System.out.println(isBalance(head));
		ReturnRes returnRes = isBalanceTree(head);
		System.out.println("returnRes.height = " + returnRes.height);
		System.out.println("returnRes.isBalance = " + returnRes.isBalance);
	}
}
