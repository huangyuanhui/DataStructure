package com.hyh.datastructure.tree.zuoshen;

//求一颗完全二叉树节点的个数（要求时间复杂度低于O(n)也即是不能用遍历）
//利用结论：假如一棵树是满二叉树 那么这棵树的节点个数是：2^L-1（L是树的高度）
public class Code_08_CompleteTreeNodeNumber {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int nodeNum(Node head) {
		/*if (head == null) {
			return 0;
		}
		return bs(head, 1, mostLeftLevel(head, 1));*/

		if (head == null) {
			return 0;
		}
		return bs(head, 1, mostLeftLevel(head, 1));
	}

	/**
	 *
	 * @param node  当前节点
	 * @param layer node在第几层
	 * @param height  高度
	 * @return
	 */
	public static int bs(Node node, int layer, int height) {
		//当前节点的层数与其左子树最左节点的层数相等
		if (layer == height) {
			return 1;
		}
		if (mostLeftLevel(node.right, layer + 1) == height) {
			//当前节点的左子树是满二叉树
			return (1 << (height - layer)) + bs(node.right, layer + 1, height);
		} else {
			//当前节点的右子树是满二叉树
			return (1 << (height - layer - 1)) + bs(node.left, layer + 1, height);
		}
	}

	//当前节点的左子树最左节点的层数
	public static int mostLeftLevel(Node node, int layer) {
		while (node != null) {
			layer++;
			node = node.left;
		}
		return layer - 1;
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		System.out.println(nodeNum(head));

	}

}
