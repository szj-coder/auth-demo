package com.example.authdemo.learn.leetcode;

/**
 * 53. 最大子数组和
 * {@see https://leetcode.cn/problems/maximum-subarray/description/}
 */
public class L53_MaxSubArray {

    public int maxSubArray(int[] nums) {
        int next = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            next = Math.max(nums[i], next + nums[i]);
            max = Math.max(next, max);
        }
        return max;
    }

}
