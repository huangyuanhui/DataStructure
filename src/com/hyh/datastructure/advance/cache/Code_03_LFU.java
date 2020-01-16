package com.hyh.datastructure.advance.cache;

import java.util.HashMap;

public class Code_03_LFU {

	public static void main(String[] args) {
		LFUCache testCache = new LFUCache(3);
		testCache.set(1, 1);
		testCache.set(2, 2);
		testCache.set(3, 3);
		System.out.println(testCache.get(1));
		System.out.println(testCache.get(2));
		testCache.set(3, 4);
		System.out.println(testCache.get(3));
		System.out.println(testCache.get(3));
	}

	public static class Node {
		public Integer key;
		public Integer value;
		public Integer times;
		public Node up;
		public Node down;

		public Node(int key, int value, int times) {
			this.key = key;
			this.value = value;
			this.times = times;
		}
	}

	public static class LFUCache {

		//自定义链表
		public static class NodeList {
			public Node head;  //头指针
			public Node tail;  //尾指针
			public NodeList last;  //上一个
			public NodeList next;  //下一个

			public NodeList(Node node) {
				head = node;
				tail = node;
			}

			//在链表头部添加节点
			public void addNodeFromHead(Node newHead) {
				newHead.down = head;
				head.up = newHead;
				head = newHead;
			}

			public boolean isEmpty() {
				return head == null;
			}

			//删除节点
			public void deleteNode(Node node) {
				if (head == tail) {
					head = null;
					tail = null;
				} else {
					if (node == head) {
						head = node.down;
						head.up = null;
					} else if (node == tail) {
						tail = node.up;
						tail.down = null;
					} else {
						node.up.down = node.down;
						node.down.up = node.up;
					}
				}
				node.up = null;
				node.down = null;
			}
		}

		private int capacity;  //缓存容量
		private int size;
		private HashMap<Integer, Node> records;
		private HashMap<Node, NodeList> heads;
		private NodeList headList;

		public LFUCache(int capacity) {
			this.capacity = capacity;
			this.size = 0;
			this.records = new HashMap<>();
			this.heads = new HashMap<>();
			headList = null;
		}

		public void set(int key, int value) {
			if (records.containsKey(key)) {
				Node node = records.get(key);
				node.value = value;
				node.times++;
				NodeList curNodeList = heads.get(node);
				move(node, curNodeList);
			} else {
				if (size == capacity) {
					Node node = headList.tail;
					headList.deleteNode(node);
					modifyHeadList(headList);
					records.remove(node.key);
					heads.remove(node);
					size--;
				}
				//创建一个Node
				Node node = new Node(key, value, 1);
				if (headList == null) {
					headList = new NodeList(node);
				} else {
					if (headList.head.times.equals(node.times)) {
						headList.addNodeFromHead(node);
					} else {
						NodeList newList = new NodeList(node);
						newList.next = headList;
						headList.last = newList;
						headList = newList;
					}
				}
				records.put(key, node);
				heads.put(node, headList);
				size++;
			}
		}

		//从缓存中删除数据
		private void move(Node node, NodeList oldNodeList) {
			oldNodeList.deleteNode(node);
			NodeList preList = modifyHeadList(oldNodeList) ? oldNodeList.last
					: oldNodeList;
			NodeList nextList = oldNodeList.next;
			if (nextList == null) {
				NodeList newList = new NodeList(node);
				if (preList != null) {
					preList.next = newList;
				}
				newList.last = preList;
				if (headList == null) {
					headList = newList;
				}
				heads.put(node, newList);
			} else {
				if (nextList.head.times.equals(node.times)) {
					nextList.addNodeFromHead(node);
					heads.put(node, nextList);
				} else {
					NodeList newList = new NodeList(node);
					if (preList != null) {
						preList.next = newList;
					}
					newList.last = preList;
					newList.next = nextList;
					nextList.last = newList;
					if (headList == nextList) {
						headList = newList;
					}
					heads.put(node, newList);
				}
			}
		}

		// return whether delete this head 返回头部的数据 无论是否删除
		private boolean modifyHeadList(NodeList nodeList) {
			if (nodeList.isEmpty()) {
				if (headList == nodeList) {
					headList = nodeList.next;
					if (headList != null) {
						headList.last = null;
					}
				} else {
					nodeList.last.next = nodeList.next;
					if (nodeList.next != null) {
						nodeList.next.last = nodeList.last;
					}
				}
				return true;
			}
			return false;
		}

		public int get(int key) {
			if (!records.containsKey(key)) {
				return -1;
			}
			Node node = records.get(key);
			node.times++;
			NodeList curNodeList = heads.get(node);
			move(node, curNodeList);
			return node.value;
		}

	}
}