package com.example.authdemo.learn.leetcode;

/**
 * 2965. 找出缺失和重复的数字
 * <a href="https://leetcode.cn/problems/find-missing-and-repeated-values/description/?envType=daily-question&envId=2024-05-31">...</a>
 */
public class L2965FindMissingAndRepeatedValues {

    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int[] mark = new int[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mark[grid[i][j] - 1]++;
            }
        }
        int x = 0, y = 0;
        for (int i = 0; i < n * n; i++) {
            if (mark[i] > 1) {
                x = i;
            }
            if (mark[i] == 0) {
                y = i;
            }
        }
        return new int[]{x + 1, y + 1};
    }

    public static void main(String[] args) {
        L2965FindMissingAndRepeatedValues l = new L2965FindMissingAndRepeatedValues();
        int[] result = l.findMissingAndRepeatedValues(new int[][]{{1, 3}, {2, 2}});
        System.out.println(result[0] + " " + result[1]);

        result = l.findMissingAndRepeatedValues(new int[][]{{9, 1, 7}, {8, 9, 2}, {3, 4, 6}});
        System.out.println(result[0] + " " + result[1]);
    }
}
