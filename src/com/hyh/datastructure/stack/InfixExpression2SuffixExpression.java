package com.hyh.datastructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//中缀转后缀
public class InfixExpression2SuffixExpression {
    public static void main(String[] args) {
        String infix = "10+((2+18)*40)-500";
        int index = 0;
        List<String> infixList = new ArrayList<>();
        while (index < infix.length()) {
            String str = infix.substring(index, index + 1);
            if (isNumberChar(str)) {
                index++;
                while (index < infix.length() && isNumberChar(infix.substring(index, index + 1))) {
                    str += infix.substring(index, index + 1);
                    index++;
                }
                index--;
            }
            infixList.add(str);
            index++;
        }
        System.out.println("infixList.toString() = " + infixList.toString());

        Stack<String> stack = new Stack<>();
        List<String> list = new ArrayList<>();
        for (String ele : infixList) {
            if (ele.matches("\\d+")) {
                list.add(ele);
            }else if (ele.matches("[*/+-]")){
                while (!stack.isEmpty() && !stack.peek().equals("(") && priority(ele) <= priority(stack.peek())) {
                    list.add(stack.pop());
                }
                stack.push(ele);
            }else if (ele.equals(")")) {
                while (!"(".equals(stack.peek())) {
                    list.add(stack.pop());
                }
                stack.pop();
            }else {
                stack.push(ele);
            }
        }
        System.out.println("stack = " + stack.toString());
        System.out.println("list = " + list.toString());

        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }

        System.out.println("list.toString() = " + list.toString());

        System.out.println("polandCal(list) = " + polandCal(list));
    }

    //逆波兰表达式计算
    public static int polandCal(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String ele : list) {
            if (ele.matches("\\d+")) {
                stack.push(ele);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                switch (ele) {
                    case "+":
                        stack.push(String.valueOf(num1 + num2));
                        break;
                    case "-":
                        stack.push(String.valueOf(num1 - num2));
                        break;
                    case "*":
                        stack.push(String.valueOf(num1 * num2));
                        break;
                    case "/":
                        stack.push(String.valueOf(num1 / num2));
                        break;
                    default:
                        break;
                }
            }
        }
        return Integer.parseInt(stack.pop());
    }

    //返回优先级
    public static int priority(String ele) {
        if (ele.equals("+") || ele.equals("-")) {
            return 0;
        }else if (ele.equals("*") || ele.equals("/")) {
            return 1;
        }
        return -1;
    }

    public static boolean isNumberChar(String str) {
        return str.matches("\\d");
    }
}
