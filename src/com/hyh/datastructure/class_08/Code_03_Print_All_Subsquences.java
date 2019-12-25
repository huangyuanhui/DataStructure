package com.hyh.datastructure.class_08;

import java.util.ArrayList;
import java.util.List;

//打印一个字符串的所有子序列
public class Code_03_Print_All_Subsquences {

	public static void printAllSubsquence(String str) {
		char[] chs = str.toCharArray();
		process(chs, 0);
	}

	public static void process(char[] chs, int i) {
		if (i == chs.length) {
			System.out.println(String.valueOf(chs));
			return;
		}
		process(chs, i + 1);
		char tmp = chs[i];
		chs[i] = 0;
		process(chs, i + 1);
		chs[i] = tmp;
	}
	
	public static void function(String str) {
		char[] chs = str.toCharArray();
		process(chs, 0, new ArrayList<Character>());
	}
	
	public static void process(char[] chs, int i, List<Character> res) {
		if(i == chs.length) {
			printList(res);
		}
		List<Character> resKeep = copyList(res);
		resKeep.add(chs[i]);
		process(chs, i+1, resKeep);
		List<Character> resNoInclude = copyList(res);
		process(chs, i+1, resNoInclude);
	}
	
	public static void printList(List<Character> res) {
		// ...;
	}
	
	public static List<Character> copyList(List<Character> list){
		return null;
	}
	

	public static void main(String[] args) {
		String test = "abc";
		//printAllSubsquence(test);
		process(test.toCharArray(), 0, "");
	}

	//打印所有子序列
	private static void process(char[] chars, int index, String res) {
		if (index == chars.length) {
			System.out.println("res = " + res);
			return;
		}
		//不要
		process(chars, index + 1, res + "*");
		//要
		process(chars, index + 1, res + chars[index]);
	}

}
