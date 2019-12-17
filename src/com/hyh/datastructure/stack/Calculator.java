package com.hyh.datastructure.stack;

//计算器
public class Calculator {
    public static void main(String[] args) {
        //String expression = "2*3+4+5-3*5+5/1";
        String expression = "40/2+3031-5*6-1";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int index = 0;
        int num1;
        int num2;
        while (index < expression.length()) {

            char temp = expression.charAt(index);
            //判断是不是运算符
            if (isOper(temp)) {
                //判断运算符栈是否为空
                if (operStack.isEmpty()) {
                    operStack.push(temp);
                }else {
                    while (!operStack.isEmpty() && priority(temp) <= priority(operStack.peek())) {
                        num2 = numStack.pop();
                        num1 = numStack.pop();
                        numStack.push(cal(num1, num2, operStack.pop()));
                    }
                    operStack.push(temp);
                }
                index++;
            }else {
                String num = String.valueOf(temp);
                index++;
                while (index < expression.length() && !isOper(expression.charAt(index))) {
                    num += expression.charAt(index);
                    index++;
                }
                System.out.println("num = " + num);
                numStack.push(Integer.parseInt(num));
            }
        }

        while (!operStack.isEmpty()) {
            num2 = numStack.pop();
            num1 = numStack.pop();
            numStack.push(cal(num1, num2, operStack.pop()));
        }
        numStack.list();
    }

    //返回运算符优先级
    public static int priority(int oper) {
        if ('+' == oper || '-' == oper) {
            return 0;
        } else if ('*' == oper || '/' == oper) {
            return 1;
        } else {
            return -1;
        }
    }

    //判断是不是运算符
    public static boolean isOper(char oper) {
        return '+' == oper || '-' == oper || '*' == oper || '/' == oper;
    }

    //num1比num2后弹出
    public static int cal(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                break;
        }
        return result;
    }
}

//栈数据结构（数组实现栈数据结构)
class ArrayStack2 {
    private int top = -1;
    private int maxSize;
    private int[] stack;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    //压/入栈
    public void push(int element) {
        if (isFull()) {
            System.out.println("栈已满。。。。。");
            return;
        }
        stack[++top] = element;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空。。。。");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空。。。。");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }

    //获取栈顶数据
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空。。。。");
        }
        return stack[top];
    }
}