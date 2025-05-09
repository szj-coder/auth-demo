package com.example.authdemo.learn.leetcode;

import java.util.Arrays;

/**
 * 198. 打家劫舍
 * {@see https://leetcode.cn/problems/house-robber/description/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china}
 */
public class L198_Rob {
    public static int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        final int[] a = new int[nums.length];
        a[0] = nums[0];
        a[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            a[i] = Math.max(a[i - 1], a[i - 2] + nums[i]);
        }
        return a[nums.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{1, 2, 3, 1}));
    }

}
