package com.hyh.datastructure.recursion;

//递归解决迷宫问题
public class MiGongDemo {
    public static void main(String[] args) {
        int[][] miGong = new int[8][7];
        for (int i = 0; i < 7; i++) {
            miGong[0][i] = 1;
            miGong[7][i] = 1;
        }
        for (int i = 1; i < 7; i++) {
            miGong[i][0] = 1;
            miGong[i][6] = 1;
        }

        miGong[3][1] = 1;
        //miGong[3][2] = 1;
        miGong[6][3] = 1;
        for (int[] ints : miGong) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        System.out.println();
        go(miGong, 1, 1);
        System.out.println();

        for (int[] ints : miGong) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    //走迷宫
    public static boolean go(int[][] miGong, int i, int j) {
        if (miGong[6][5] == 2) {
            return true;
        } else {
            if (miGong[i][j] == 0) {
                miGong[i][j] = 2;
                if (go(miGong, i + 1, j)) {  //下
                    return true;
                } else if (go(miGong, i, j + 1)) {  //右
                    return true;
                } else if (go(miGong, i - 1, j)) {  //上
                    return true;
                } else if (go(miGong, i, j - 1)) {  //左
                    return true;
                }else {
                    miGong[i][j] = 0;
                    return false;
                }
            }else { //miGong[i][j] == 1 or miGong[i][j] == 2 or miGong[i][j] == 3
                return false;
            }
        }
    }
}
