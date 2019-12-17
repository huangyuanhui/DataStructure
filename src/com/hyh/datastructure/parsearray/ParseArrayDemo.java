package com.hyh.datastructure.parsearray;

public class ParseArrayDemo {
    public static void main(String[] args) {
        int[][] array = new int[11][11];
        array[1][2] = 1;
        array[2][3] = 2;

        int sum = 0;
        for (int[] ints : array) {
            for (int anInt : ints) {
                if (anInt != 0) {
                    sum++;
                }
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        System.out.println("sum = " + sum);

        //压缩成稀疏数组
        int[][] parseArr = new int[sum + 1][3];
        parseArr[0][0] = array.length;
        parseArr[0][1] = array[0].length;
        parseArr[0][2] = sum;

        int row = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] != 0) {
                    row++;
                    parseArr[row][0] = i;
                    parseArr[row][1] = j;
                    parseArr[row][2] = array[i][j];
                }
            }
        }
        for (int[] ints : parseArr) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        //还原成原数组
        int[][] array1 = new int[parseArr[0][0]][parseArr[0][1]];
        for (int i = 1; i < parseArr.length; i++) {
            array1[parseArr[i][0]][parseArr[i][1]] = parseArr[i][2];
        }
        for (int[] ints : array1) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
