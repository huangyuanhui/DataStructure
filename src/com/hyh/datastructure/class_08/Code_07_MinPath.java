package com.hyh.datastructure.class_08;

//从矩阵左上角到达右下角 最短的路径是多少？ 路径等于经过坐标值相加
//动态规划（用空间换时间）
public class Code_07_MinPath {

    public static int minPath1(int[][] matrix) {
        return process1(matrix, matrix.length - 1, matrix[0].length - 1);
    }

    public static int process1(int[][] matrix, int i, int j) {
        int res = matrix[i][j];
        if (i == 0 && j == 0) {
            return res;
        }
        if (i == 0 && j != 0) {
            return res + process1(matrix, i, j - 1);
        }
        if (i != 0 && j == 0) {
            return res + process1(matrix, i - 1, j);
        }
        return res + Math.min(process1(matrix, i, j - 1), process1(matrix, i - 1, j));
    }

    //暴力递归改动态规划
    public static int minPath2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        //动态规划表 以空间换时间
        int[][] dp = new int[row][col];

        //base case
        dp[0][0] = m[0][0];
        //base case
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        //base case
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }

        //规律
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }


    public static void main(String[] args) {
        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        System.out.println(minPath1(m));
        System.out.println(minPath2(m));

        m = generateRandomMatrix(6, 7);
        System.out.println(minPath1(m));
        System.out.println(minPath2(m));

        System.out.println("process(m, 0, 0) = " + process(m, 0, 0));
    }

    // 生成指定行列的随机矩阵
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 10);
            }
        }
        return result;
    }

    //只能向下和向右走（暴力递归 有大量的重复解产生）
    private static int process(int[][] matrix, int row, int column) {
        if (row == matrix.length - 1 && column == matrix[0].length - 1) {
            return matrix[row][column];
        }
        if (row == matrix.length - 1) {
            return matrix[row][column] + process(matrix, row, column + 1);
        }
        if (column == matrix[0].length - 1) {
            return matrix[row][column] + process(matrix, row + 1, column);
        }

        //往下走
        int down = matrix[row][column] + process(matrix, row + 1, column);
        //往右走
        int right = matrix[row][column] + process(matrix, row, column + 1);
        //往回窜 选出较短的返回
        return Math.min(down, right);
    }

    //暴力递归改动态规划
    private static int minPath(int[][] matrix) {
	/*	if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return 0;
		}
		int row = matrix.length;
		int column = matrix[0].length;
		int[][] dp = new int[row][column];
		dp[0][0] = matrix[0][0];
		for (int i = column - 1; i >= 0; i--) {
			dp[i][column - 1] =
		}
*/
        return 0;
    }
}
