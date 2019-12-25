package com.hyh.datastructure.class_08;

//母牛每年生一致母牛 新出生的母牛成长三年后也能生一只母牛 假设牛不会死 最开始一头牛 求N年后  母牛的数量
//结论： f(n) = f(n - 1) + f(n- 3) 牛不会死 去年的牛会保留到今年 三年前的牛都会生牛
public class Code_05_Cow {

	public static int cowNumber1(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1 || n == 2 || n == 3) {
			return n;
		}
		return cowNumber1(n - 1) + cowNumber1(n - 3);
	}

	public static int cowNumber2(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1 || n == 2 || n == 3) {
			return n;
		}
		int res = 3;
		int pre = 2;
		int prepre = 1;
		int tmp1 = 0;
		int tmp2 = 0;
		for (int i = 4; i <= n; i++) {
			tmp1 = res;
			tmp2 = pre;
			res = res + prepre;
			pre = tmp1;
			prepre = tmp2;
		}
		return res;
	}

	public static void main(String[] args) {
		int n = 20;
		System.out.println(cowNumber1(n));
		System.out.println(cowNumber2(n));
		System.out.println("cowCount(10) = " + cowCount(20));
	}

	private static int cowCount(int year) {
		if (year <= 0) {
			return 0;
		}
		if (year == 1 || year == 2 || year == 3) {
			return year;
		}
		return cowCount(year - 1) + cowCount(year - 3);
	}
}
