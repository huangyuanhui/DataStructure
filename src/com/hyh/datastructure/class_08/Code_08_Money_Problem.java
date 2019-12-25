package com.hyh.datastructure.class_08;

//给定一个数组arr 和一个整数aim 可以任意选择arr中的元素 能否累加得到aim 返回true或者false
public class Code_08_Money_Problem {

    public static boolean money1(int[] arr, int aim) {
        return process1(arr, 0, 0, aim);
    }

    public static boolean process1(int[] arr, int i, int sum, int aim) {
        if (sum == aim) {
            return true;
        }
        // sum != aim
        if (i == arr.length) {
            return false;
        }
        return process1(arr, i + 1, sum, aim) || process1(arr, i + 1, sum + arr[i], aim);
    }

    //暴力递归改动态规划（用空间换时间）
    public static boolean money2(int[] arr, int aim) {
        boolean[][] dp = new boolean[arr.length + 1][aim + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][aim] = true;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = aim - 1; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j];
                if (j + arr[i] <= aim) {
                    dp[i][j] = dp[i][j] || dp[i + 1][j + arr[i]];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 8};
        int aim = 12;
        System.out.println(money1(arr, aim));
        System.out.println(money2(arr, aim));

        System.out.println("isSum(arr, 0, 0, aim) = " + isSum(arr, 0, 0, aim));
    }


    /**
     *
     * @param arr
     * @param sum 当前和
     * @param index
     * @param aim 目标和
     * @return
     */
    public static boolean isSum(int[] arr, int sum, int index, int aim) {
        if (sum == aim) {
            return true;
        }
        if (index == arr.length) {
            return false;
        }
        return isSum(arr, sum, index + 1, aim) || isSum(arr, sum + arr[index], index + 1, aim);
    }

}
