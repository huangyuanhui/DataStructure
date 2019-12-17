package com.hyh.datastructure.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//逆波兰表达式
public class PolandNotation {
    public static void main(String[] args) {
        //(3 + 4) * 5 -6
        String suffixExpression = "30 4 + 5 * 6 -";
        List<String> list = new ArrayList<>(Arrays.asList(suffixExpression.split(" ")));
        System.out.println("list = " + list.toString());
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
        System.out.println("stack.peek() = " + stack.peek());
    }
}
