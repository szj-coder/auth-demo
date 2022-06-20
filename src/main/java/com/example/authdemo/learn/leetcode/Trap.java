package com.example.authdemo.learn.leetcode;

import java.util.Arrays;

/**
 * 42. 接雨水
 * {@see https://leetcode-cn.com/problems/trapping-rain-water/}
 */
public class Trap {

    public int trap(int[] height) {
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            leftMax[i] = max;
            if (height[i] > max)
                max = height[i];
        }
        max = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            rightMax[i] = max;
            if (height[i] > max)
                max = height[i];
        }

        max = 0;
        for (int i = 1; i < height.length - 1; i++) {
            if (leftMax[i] < height[i] || rightMax[i] < height[i]) {
                continue;
            }
            max += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        System.out.println(Arrays.toString(height));
        System.out.println(Arrays.toString(leftMax));
        System.out.println(Arrays.toString(rightMax));
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Trap().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

}
