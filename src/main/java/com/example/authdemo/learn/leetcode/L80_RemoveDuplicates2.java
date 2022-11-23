package com.example.authdemo.learn.leetcode;

import java.util.Arrays;

/**
 * 80. 删除有序数组中的重复项 II
 * {@see https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/}
 */
public class L80_RemoveDuplicates2 {

    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int i = 0;
        int n = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] == nums[j]) {
                n++;
                nums[i + 1] = nums[j];
                continue;
            }
            i += n == 0 ? 1 : 2;
            n = 0;
            nums[i] = nums[j];
        }
        return i + 1 + (n == 0 ? 0 : 1);
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }
}
