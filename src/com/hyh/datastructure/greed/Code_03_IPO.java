package com.hyh.datastructure.greed;

import java.util.Comparator;
import java.util.PriorityQueue;

//做m个项目 最大收益
public class Code_03_IPO {
	public static class Node {
		//收益
		public int p;
		//花费
		public int c;

		public Node(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}

	//比较器 那个项目花费低那个项目放在上面（就是形成小根堆）
	public static class MinCostComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.c - o2.c;
		}

	}

	//比较器 哪个项目收益高哪个项目就放在上面（就是形成大根堆）
	public static class MaxProfitComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o2.p - o1.p;
		}

	}

	//找到最大收益

	/**
	 *
	 * @param k 最多可以做少个项目
	 * @param W  起初资金
	 * @param Profits
	 * @param Capital
	 * @return
	 */
	public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
		Node[] nodes = new Node[Profits.length];
		for (int i = 0; i < Profits.length; i++) {
			nodes[i] = new Node(Profits[i], Capital[i]);
		}

		//最小花费堆
		PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
		//最大收益堆
		PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
		for (int i = 0; i < nodes.length; i++) {
			minCostQ.add(nodes[i]);
		}
		for (int i = 0; i < k; i++) {
			while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
				maxProfitQ.add(minCostQ.poll());
			}
			//做不到K个项目（因为 本金 < 项目花费）
			if (maxProfitQ.isEmpty()) {
				return W;
			}
			//做项目了 做完项目总资金=本金+收益
			W += maxProfitQ.poll().p;
		}
		return W;
	}

}
