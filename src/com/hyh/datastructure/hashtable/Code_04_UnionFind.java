package com.hyh.datastructure.hashtable;

import java.util.HashMap;
import java.util.List;

//并查集
public class Code_04_UnionFind {

	public static class Node {
	}

	//并查集数据结构（用map实现）
	public static class UnionFindSet {
		//key:某一个节点 value：该节点的父节点
		public HashMap<Node, Node> fatherMap;
		//某一个节点所在的集合一共有多少个节点
		public HashMap<Node, Integer> sizeMap;

		public UnionFindSet(List<Node> nodes) {
			fatherMap = new HashMap<Node, Node>();
			sizeMap = new HashMap<Node, Integer>();
			//初始化：集合必须是给定好的 不是动态变化的
			makeSets(nodes);
		}

		public void makeSets(List<Node> nodes) {
			fatherMap.clear();
			sizeMap.clear();
			for (Node node : nodes) {
				fatherMap.put(node, node);
				sizeMap.put(node, 1);
			}
		}

		private Node findHead(Node node) {
			Node father = fatherMap.get(node);
			if (father != node) {
				father = findHead(father);
			}
			fatherMap.put(node, father);
			return father;
		}
		
		public boolean isSameSet(Node a, Node b) {
			return findHead(a) == findHead(b);
		}

		public void union(Node a, Node b) {
			if (a == null || b == null) {
				return;
			}
			Node aHead = findHead(a);
			Node bHead = findHead(b);
			if (aHead != bHead) {
				int aSetSize= sizeMap.get(aHead);
				int bSetSize = sizeMap.get(bHead);
				if (aSetSize <= bSetSize) {
					fatherMap.put(aHead, bHead);
					sizeMap.put(bHead, aSetSize + bSetSize);
				} else {
					fatherMap.put(bHead, aHead);
					sizeMap.put(aHead, aSetSize + bSetSize);
				}
			}
		}

	}

	public static void main(String[] args) {

	}

}
