package com.example.authdemo.learn.leetcode;

import java.util.Arrays;

/**
 * 26. 删除有序数组中的重复项
 * {@see https://leetcode.cn/problems/remove-duplicates-from-sorted-array/}
 */
public class L26_RemoveDuplicates {
    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] == nums[j]) {
                continue;
            }
            nums[++i] = nums[j];
        }
        return i + 1;
    }

    public static void main(String[] args) {
        final int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }
}
