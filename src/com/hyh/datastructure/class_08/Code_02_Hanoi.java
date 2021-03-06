package com.hyh.datastructure.class_08;

//汉诺塔问题
public class Code_02_Hanoi {

    public static void hanoi(int n) {
        if (n > 0) {
            func(n, n, "left", "mid", "right");
        }
    }

    /**
     * @param N    盘子数
     * @param from
     * @param help
     * @param to
     */
    public static void process(int N, String from, String to, String help) {
        if (N == 1) {
            System.out.println("Move " + N + " from " + from + " to " + to);
        } else {
            process(N - 1, from, help, to);
            System.out.println("Move " + N + " from " + from + " to " + to);
            process(N - 1, help, to, from);
        }
    }

    //汉诺塔
    public static void func(int rest, int down, String from, String help, String to) {
        if (rest == 1) {
            System.out.println("move " + down + " from " + from + " to " + to);
        } else {

            func(rest - 1, down - 1, from, to, help);
            func(1, down, from, help, to);
            func(rest - 1, down - 1, help, from, to);
        }
    }

    public static void moveLeftToRight(int N) {
        if (N == 1) {
            System.out.println("move 1 from left to right");
        }
        moveLeftToMid(N - 1);
        System.out.println("move " + N + "from left to right");
        moveMidToRight(N - 1);
    }

    public static void moveRightToLeft(int N) {

    }

    public static void moveLeftToMid(int N) {
        if (N == 1) {
            System.out.println("move 1 from left to mid");
        }
        moveLeftToRight(N - 1);
        System.out.println("move " + N + "from left to mid");
        moveRightToMid(N - 1);
    }

    public static void moveMidToLeft(int N) {

    }

    public static void moveRightToMid(int N) {

    }

    public static void moveMidToRight(int N) {
        if (N == 1) {
            System.out.println("move 1 from mid to right");
        }
        moveMidToLeft(N - 1);
        System.out.println("move " + N + "from mid to right");
        moveLeftToRight(N - 1);
    }

    public static void main(String[] args) {
        int n = 4;
        //O(2^n)
        process(n, "左盘子", "右盘子", "辅助盘子");
    }

}
