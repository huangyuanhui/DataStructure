package com.hyh.datastructure.recursion;

//递归解决八皇后问题
public class Queen8 {
    public static void main(String[] args) {
        int[] chessBoard = new int[8];
        settleChess(chessBoard, 0);
        System.out.println("count = " + count);
    }

    private static int count;
    //放置每一行的棋子
    public static void settleChess(int[] chessBoard, int row) {
        if (row == chessBoard.length) {
            //已然放置完
            printChessBoard(chessBoard);
            count++;
            return;
        }
        //列
        for (int column = 0; column < chessBoard.length; column++) {
            chessBoard[row] = column;
            if (judge(chessBoard, row)){
                settleChess(chessBoard, row + 1);
            }
        }
    }

    //判断摆放的棋子是否与摆放好的冲突
    public static boolean judge(int[] chessBoard, int row) {
        for (int i = 0; i < row; i++) {
            if (chessBoard[i] == chessBoard[row] || Math.abs(row - i) == Math.abs(chessBoard[row] - chessBoard[i])) {
                return false;
            }
        }
        return true;
    }

    //打印棋盘
    public static void printChessBoard(int[] chessBoard) {
        for (int i = 0; i < chessBoard.length; i++) {
            System.out.print(chessBoard[i] + " ");
        }
        System.out.println();
    }
}
