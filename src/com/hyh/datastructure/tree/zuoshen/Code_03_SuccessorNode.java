package com.hyh.datastructure.tree.zuoshen;

//找到一个节点的后继节点（避免遍历整颗树的情况下（复杂度就变成这个节点到其后继节点的长度））？
//1：当一个节点有右子树 他的后继节点就是他右子树的最左的节点
//2：若一个节点没有右子树 那么就要找哪一个节点的左子树是以这个节点结尾的 这个节点就是当前节点的后继节点
//后继节点 在中序遍历的序列中 一个节点后面的节点 叫做这个节点的后继节点
//前驱节点 在中序遍历的序列中 一个节点前面的节点 叫做这个节点的前驱节点
public class Code_03_SuccessorNode {

	public static class Node {
		public int value;
		public Node left;
		public Node right;
		public Node parent;

		public Node(int data) {
			this.value = data;
		}
	}

	//获取一个节点的前驱节点
	public static Node getPrecursorNode(Node node) {
        if (node == null) {
            return node;
        }
        if (node.left != null) {
            //获取左子树最右的节点
            return getMostRight(node.left);
        }else {
            Node parent = node.parent;
            while (parent != null && parent.right != node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    //获取左子树最右的节点
    private static Node getMostRight(Node node) {
        while (node != null) {
            node = node.left;
        }
        return node;
    }

    public static Node getSuccessorNode(Node node) {
		/*if (node == null) {
			return node;
		}
		if (node.right != null) {
			return getLeftMost(node.right);
		} else {
			Node parent = node.parent;
			while (parent != null && parent.left != node) {
				node = parent;
				parent = node.parent;
			}
			return parent;
		}*/

		if (node == null) {
			return node;
		}
		if (node.right != null) {
			//窜到右子树最左的节点
			return getMostLeft(node.right);
		} else {
			Node parent = node.parent;
			//窜到一个节点的左子树是以这个节点结尾的那个节点
			while (parent != null && parent.left != node) {
				node = parent;
				parent = node.parent;
			}
			return parent;
		}
	}

	private static Node getMostLeft(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	public static void main(String[] args) {
		Node head = new Node(6);
		head.parent = null;
		head.left = new Node(3);
		head.left.parent = head;
		head.left.left = new Node(1);
		head.left.left.parent = head.left;
		head.left.left.right = new Node(2);
		head.left.left.right.parent = head.left.left;
		head.left.right = new Node(4);
		head.left.right.parent = head.left;
		head.left.right.right = new Node(5);
		head.left.right.right.parent = head.left.right;
		head.right = new Node(9);
		head.right.parent = head;
		head.right.left = new Node(8);
		head.right.left.parent = head.right;
		head.right.left.left = new Node(7);
		head.right.left.left.parent = head.right.left;
		head.right.right = new Node(10);
		head.right.right.parent = head.right;

		Node test = head.left.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left.left.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left.right.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right.left.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right.right; // 10's next is null
		System.out.println(test.value + " next: " + getSuccessorNode(test));
	}

}
