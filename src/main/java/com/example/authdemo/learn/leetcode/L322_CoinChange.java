package com.example.authdemo.learn.leetcode;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * {@see https://leetcode.cn/problems/coin-change/description/}
 */
public class L322_CoinChange {
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int[] num = new int[amount + 1];
        Arrays.fill(num, -1);
        num[0] = 0;

        for (int i = 0; i < amount + 1; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    if (num[i - coins[j]] != -1) {
                        if (num[i] == -1) {
                            num[i] = num[i - coins[j]] + 1;
                        } else {
                            num[i] = Math.min(num[i - coins[j]] + 1, num[i]);
                        }
                    }
                }
            }
        }
        return num[amount];
    }

    public static void main(String[] args) {
        int i = coinChange(new int[]{1, 2, 5}, 11);
        System.out.println(i);

        i = coinChange(new int[]{2}, 3);
        System.out.println(i);
    }

}
