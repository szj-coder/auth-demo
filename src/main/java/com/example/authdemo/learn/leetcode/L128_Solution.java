package com.example.authdemo.learn.leetcode;

import java.util.Arrays;

/**
 * 128. 最长连续序列
 * {@see https://leetcode.cn/problems/longest-consecutive-sequence/description/}
 */
public class L128_Solution {
    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int target = nums[i];
            int num = 1;
            while (j < nums.length && (nums[j] == target || nums[j] == target + 1)) {
                if (nums[j] == target + 1) {
                    num++;
                    target = nums[j];
                }
                j++;
            }
            i = j - 1;
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        L128_Solution l128Solution = new L128_Solution();
        int result = l128Solution.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1});
        System.out.println(result);
        result = l128Solution.longestConsecutive(new int[]{1,0,1,2});
        System.out.println(result);
        result = l128Solution.longestConsecutive(new int[]{100,4,200,1,3,2});
        System.out.println(result);
    }
}
