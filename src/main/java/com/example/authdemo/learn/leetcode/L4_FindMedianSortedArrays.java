package com.example.authdemo.learn.leetcode;

/**
 * 4. 寻找两个正序数组的中位数
 * {@see https://leetcode-cn.com/problems/median-of-two-sorted-arrays/}
 */
public class L4_FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums = new int[2000];
        int i, j, k;
        i = j = k = 0;
        while (i < nums1.length || j < nums2.length) {
            if (j >= nums2.length || (i < nums1.length && nums1[i] < nums2[j])) {
                nums[k++] = nums1[i++];
            } else {
                nums[k++] = nums2[j++];
            }
        }
        return k % 2 == 0 ? (nums[k / 2] + nums[k / 2 - 1]) * 1.0 / 2 : nums[k / 2];
    }

    public static void main(String[] args) {
        System.out.println(new L4_FindMedianSortedArrays().findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
        System.out.println(new L4_FindMedianSortedArrays().findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }
}
