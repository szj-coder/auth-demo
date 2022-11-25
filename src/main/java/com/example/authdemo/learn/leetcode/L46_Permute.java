package com.example.authdemo.learn.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. 全排列 II
 * {@see https://leetcode.cn/problems/permutations-ii/description/}
 */
public class L46_Permute {
    public static List<List<Integer>> permute(int[] nums) {
        ArrayList<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] mark = new boolean[nums.length];
        Integer[] result = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (mark[i] || (i > 0 && nums[i] == nums[i - 1] && !mark[i - 1])) {
                continue;
            }
            mark[i] = true;
            result[0] = nums[i];
            recursion(1, nums, mark, result, results);
            mark[i] = false;
        }
        return new ArrayList<>(results);
    }

    public static void recursion(int current, int[] nums, boolean[] mark, Integer[] result, List<List<Integer>> results) {
        if (current >= nums.length) {
            results.add(new ArrayList<>(Arrays.asList(result)));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (mark[i] || (i > 0 && nums[i] == nums[i - 1] && !mark[i - 1])) {
                continue;
            }
            mark[i] = true;
            result[current] = nums[i];
            recursion(current + 1, nums, mark, result, results);
            mark[i] = false;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> results = permute(new int[]{1, 1, 2});
        System.out.println(results);
    }

}
