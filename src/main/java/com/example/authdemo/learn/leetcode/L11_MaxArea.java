package com.example.authdemo.learn.leetcode;

/**
 * 11. 盛最多水的容器
 * {@see https://leetcode.cn/problems/container-with-most-water/description/}
 */
public class L11_MaxArea {

    public static int maxArea(int[] height) {
        final int length = height.length;
        int x = 0 ,y = length -1;
        int maxArea = 0;
        while (y - x > 0) {
            int minHeight = height[x] > height[y]? height[y]: height[x];
            int area = minHeight * (y-x);
            maxArea = area > maxArea? area: maxArea;
            if (height[x] > height[y]) {
                y--;
            } else {
                x++;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        final int result = maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        System.out.println(result);
    }
}
