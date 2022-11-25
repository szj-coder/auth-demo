package com.example.authdemo.learn.leetcode;

/**
 * 10. 正则表达式匹配
 * {@see https://leetcode-cn.com/problems/regular-expression-matching/}
 */
public class L10_IsMatch {

    public static void main(String[] args) {
        System.out.println(new L10_IsMatch().isMatch("ababbb", "a.*b"));
        System.out.println(new L10_IsMatch().isMatch("aa", "a"));
        System.out.println(new L10_IsMatch().isMatch("aa", "a*"));
        System.out.println(new L10_IsMatch().isMatch("aab", "c*a*b"));
        System.out.println(new L10_IsMatch().isMatch("ab", ".*"));
    }

    public boolean isMatch(String s, String p) {
        boolean[][] bytes = new boolean[s.length() + 1][p.length() + 1];
        bytes[0][0] = true;
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        for (int j = 1; j < p.length() + 1; j++) {
            for (int i = 0; i < s.length() + 1; i++) {
                if (pChars[j - 1] == '*') {
                    bytes[i][j] = bytes[i][j - 2] || (match(i == 0 ? ' ' : sChars[i - 1], pChars[j - 2])
                            && (i != 0 && (bytes[i - 1][j - 2] || bytes[i - 1][j])));
                } else {
                    bytes[i][j] = match(i == 0 ? ' ' : sChars[i - 1], pChars[j - 1])
                            && (i != 0 && bytes[i - 1][j - 1]);
                }
            }
        }
        return bytes[s.length()][p.length()];
    }

    public boolean match(char s, char p) {
        return p == '.' || s == p;
    }
}
