package com.hyh.datastructure.advance.searchtree;

import java.util.HashMap;

/**
 * 给定一个数组arr，你可以任意把arr分成很多不相容的子数组，你的目的是：
 *   分出来的子数组中，异或和为0的子数组最多
 */
public class Most_ERO {
    public static int mostEOR(int[] arr) {
        int ans = 0;
        int xor = 0;//异或，英文为exclusive OR，缩写成xor
        int[] dp = new int[arr.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);//一个数都没有的时候，异或和为0
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            if (map.containsKey(xor)) {//存在证明异或和区域+1
                int pre = map.get(xor);
                dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
            }
            if (i > 0) {//拿最优情况,类似贪心
                dp[i] = Math.max(dp[i - 1], dp[i]);
            }
            //求最晚的位置，所以每次都更新
            map.put(xor, i);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] eors = new int[arr.length];
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
            eors[i] = eor;
        }
        int[] mosts = new int[arr.length];
        mosts[0] = arr[0] == 0 ? 1 : 0;
        for (int i = 1; i < arr.length; i++) {
            mosts[i] = eors[i] == 0 ? 1 : 0;
            for (int j = 0; j < i; j++) {
                if ((eors[i] ^ eors[j]) == 0) {
                    mosts[i] = Math.max(mosts[i], mosts[j] + 1);
                }
            }
            mosts[i] = Math.max(mosts[i], mosts[i - 1]);
        }
        return mosts[mosts.length - 1];
    }
    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }
    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 300;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int res = mostEOR(arr);
            int comp = comparator(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
