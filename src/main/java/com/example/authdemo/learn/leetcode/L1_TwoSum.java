package com.example.authdemo.learn.leetcode;

import java.util.HashMap;

public class L1_TwoSum {

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    /**
     * 更快
     */
    public int[] twoSum2(int[] nums, int target) {
        final HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                final Integer i1 = map.get(target - nums[i]);
                return new int[]{i1, i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
