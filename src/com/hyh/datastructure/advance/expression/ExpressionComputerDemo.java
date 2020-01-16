package com.hyh.datastructure.advance.expression;

import java.util.LinkedList;

public class ExpressionComputerDemo {
    //将字符串表达计算表达式结果
    public static int getValue(String str) {
        return computer(str.toCharArray(), 0)[0];
    }

    //返回数组 数组长度一定为2 (arr[0]代表计算结果，arr[1]代表计算到哪个位置)
    private static int[] computer(char[] chars, int i) {
        LinkedList<String> queue = new LinkedList();
        int pre = 0;
        int[] bra = null;
        while (i < chars.length && chars[i] != ')') {
            if (chars[i] >= '0' && chars[i] <= '9') {
                //遍历到的是数值字符
                pre = pre * 10 + chars[i++] - '0';
            } else if (chars[i] != '(') {
                //遍历到的是运算符 将pre与运算符相关数据收集进双端队列
                addNum(queue, pre);
                queue.addLast(String.valueOf(chars[i++]));
                //pre重置为0
                pre = 0;
            } else {
                //遍历到i位置的字符是左括号 进递归 将左括号后一位一直到这个左括号与之对应的右括号
                //之间的计算结果返回 视作黑盒
                bra = computer(chars, i + 1);
                pre = bra[0];
                i = bra[1] + 1;
            }
        }
        addNum(queue, pre);
        return new int[]{getNum(queue), i};
    }

    //最后只有计算加减
    public static int getNum(LinkedList<String> que) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!que.isEmpty()) {
            cur = que.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }

    //向双端队列添加字符
    private static void addNum(LinkedList<String> queue, int num) {
        if (!queue.isEmpty()) {
            String top = queue.pollLast();
            if (top.equals("+") || top.equals("-")) {
                //队列poll出来的是加减符
                queue.addLast(top);//重新放回去
            } else {
                //队列poll出来的是乘除符 再从队列poll出数据
                int cur = Integer.valueOf(queue.pollLast());
                num = top.equals("*") ? (cur * num) : (cur / num);
            }
        }
        queue.addLast(String.valueOf(num));
    }
}
