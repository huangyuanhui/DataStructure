package com.hyh.datastructure.matrix;

//岛问题
public class Islands {

	public static int countIslands(int[][] m) {
		if (m == null || m[0] == null) {
			return 0;
		}
		int N = m.length;
		int M = m[0].length;
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (m[i][j] == 1) {
					infect(m, i, j, N, M);
					res++;
				}
			}
		}
		return res;
	}

	//感染函数
	public static void infect(int[][] m, int i, int j, int N, int M) {
		//判断坐标是否越界 和 某座标是否可以感染
		if (i < 0 || i >= N || j < 0 || j >= M || m[i][j] != 1) {
			return;
		}
		//感染该坐标
		m[i][j] = 2;
		//感染该坐标的上下左右的坐标
		infect(m, i - 1, j, N, M);
		infect(m, i + 1, j, N, M);
		infect(m, i, j - 1, N, M);
		infect(m, i, j + 1, N, M);
	}

	public static void main(String[] args) {
		int[][] m1 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				        { 0, 1, 1, 1, 0, 1, 1, 1, 0 }, 
				        { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
				        { 0, 1, 1, 0, 0, 0, 0, 0, 0 }, 
				        { 0, 0, 0, 0, 0, 1, 1, 0, 0 }, 
				        { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
				        { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
		System.out.println(countIslands(m1));

		int[][] m2 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
						{ 0, 1, 1, 1, 1, 1, 1, 1, 0 }, 
						{ 0, 1, 1, 1, 0, 0, 0, 1, 0 },
						{ 0, 1, 1, 0, 0, 0, 1, 1, 0 }, 
						{ 0, 0, 0, 0, 0, 1, 1, 0, 0 }, 
						{ 0, 0, 0, 0, 1, 1, 1, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
		System.out.println(countIslands(m2));

	}

}
