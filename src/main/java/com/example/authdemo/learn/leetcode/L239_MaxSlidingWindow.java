package com.example.authdemo.learn.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 239. 滑动窗口最大值
 * {@see https://leetcode-cn.com/problems/sliding-window-maximum/}
 */
public class L239_MaxSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        final PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> -a[0]));
        int i = 0;
        while (i < k - 1) {
            priorityQueue.add(new int[]{nums[i], i});
            i++;
        }
        final int[] ret = new int[nums.length - k + 1];
        while (i < nums.length) {
            priorityQueue.add(new int[]{nums[i], i});
            i++;
//            System.out.println(String.format("%s %s %s", i, k, Arrays.toString(priorityQueue.peek())));
            while (priorityQueue.peek()[1] < i - k) {
                priorityQueue.poll();
//                System.out.println(String.format("%s %s %s", i, k, Arrays.toString(priorityQueue.peek())));
            }
//            System.out.println();
            ret[i - k] = priorityQueue.peek()[0];
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new L239_MaxSlidingWindow().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println(Arrays.toString(new L239_MaxSlidingWindow().maxSlidingWindow(new int[]{1}, 1)));
        System.out.println(Arrays.toString(new L239_MaxSlidingWindow().maxSlidingWindow(new int[]{1, -1}, 1)));
        System.out.println(Arrays.toString(new L239_MaxSlidingWindow().maxSlidingWindow(new int[]{-7, -8, 7, 5, 7, 1, 6, 0}, 4)));
    }
}
