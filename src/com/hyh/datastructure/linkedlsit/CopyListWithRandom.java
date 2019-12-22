package com.hyh.datastructure.linkedlsit;

import java.util.HashMap;

//复制拷贝含有随机节点的链表
public class CopyListWithRandom {


	public static class Node {
		public int value;
		public Node next;
		public Node rand;

		public Node(int data) {
			this.value = data;
		}
	}

	//方法1：额外空间O(n)
	public static Node copyListWithRand1(Node head) {
		//hashMap就是额外空间
		HashMap<Node, Node> map = new HashMap<>();
		Node curr = head;
		while (curr != null) {
			//拷贝节点
			Node copyNode = new Node(curr.value);
			map.put(curr, copyNode);
			curr = curr.next;
		}

		//深度拷贝
		curr = head;
		while (curr != null) {
			map.get(curr).next = map.get(curr.next);
			map.get(curr).rand = map.get(curr.rand);
			curr = curr.next;
		}

		//返回拷贝链表的头节点
		return map.get(head);
	}

	//方式二：额外空间O(1)
	public static Node copyListWithRand2(Node head) {
		if (head == null) {
			return null;
		}
		Node cur = head;
		//当前节点的next节点
		Node next = null;
		// copy node and link to every node
		while (cur != null) {
			next = cur.next;
			cur.next = new Node(cur.value);
			cur.next.next = next;
			cur = next;
		}

		cur = head;
		Node curCopy = null;
		// set copy node rand
		while (cur != null) {
			curCopy = cur.next;
			next = cur.next.next;
			curCopy.rand = cur.rand == null ? null : cur.rand.next;
			cur = next;
		}

		Node res = head.next;
		cur = head;
		// split
		while (cur != null) {
			next = cur.next.next;
			curCopy = cur.next;
			cur.next = next;
			curCopy.next = next != null ? next.next : null;
			cur = next;
		}
		return res;
	}

	public static void printRandLinkedList(Node head) {
		Node cur = head;
		System.out.print("order: ");
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println();
		cur = head;
		System.out.print("rand:  ");
		while (cur != null) {
			System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
			cur = cur.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = null;
		Node res1 = null;
		Node res2 = null;
		printRandLinkedList(head);
		res1 = copyListWithRand1(head);
		printRandLinkedList(res1);
		res2 = copyListWithRand2(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);

		head.rand = head.next.next.next.next.next; // 1 -> 6
		head.next.rand = head.next.next.next.next.next; // 2 -> 6
		head.next.next.rand = head.next.next.next.next; // 3 -> 5
		head.next.next.next.rand = head.next.next; // 4 -> 3
		head.next.next.next.next.rand = null; // 5 -> null
		head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

		printRandLinkedList(head);
		res1 = copyListWithRand1(head);
		printRandLinkedList(res1);
		res2 = copyListWithRand2(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

	}

}
