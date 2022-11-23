package com.example.authdemo.learn.leetcode;

/**
 * 136. 只出现一次的数字
 * {@see https://leetcode.cn/problems/single-number/}
 */
public class L136_SingleNumber {
    public static int singleNumber(int[] nums) {
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        System.out.println(singleNumber(nums));
    }
}
