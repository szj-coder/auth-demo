package com.example.authdemo.learn.leetcode;

/**
 * 3. 无重复字符的最长子串
 * {@see https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/}
 */
class L3_LengthOfLongestSubstring {
    public static void main(String[] args) {
        System.out.println(new L3_LengthOfLongestSubstring().lengthOfLongestSubstring("aab"));
    }

    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int[] marks = new int[200];
        int end = 0;
        int count = 0;
        int max = 0;
        for (int i = 0; i < chars.length; i++) {
            marks[chars[i]]++;
            count++;
            while (marks[chars[i]] > 1) {
                marks[chars[end++]]--;
                count--;
            }
            if (count > max) max = count;
        }
        return max;
    }
}