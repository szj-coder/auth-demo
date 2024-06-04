package com.example.authdemo.learn.leetcode;

import java.util.Arrays;

/**
 * 70. 爬楼梯
 * {@see https://leetcode.cn/problems/climbing-stairs/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china}
 */
public class L70ClimbStairs {
    /**
     * 3
     * 1 1 1
     * 1 2
     * 2 1
     *
     * 4
     * 1 1 1 1
     * 2 1 1
     * 1 2 1
     * 1 1 2
     * 2 2
     *
     * 5
     * 1 1 1 1 1
     * 2 1 1 1
     * 1 2 1 1
     * 1 1 2 1
     * 1 1 1 2
     * 2 2 1
     * 2 1 2
     * 1 2 2
     *
     * 1 2 3 5 8
     */
    public static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] list = new int[n];
        list[0] = 1;
        list[1] = 2;
        for (int i = 2; i < n; i++) {
            list[i] = list[i -1] + list[i -2];
        }
        return list[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(5));
    }
}
