package com.example.authdemo.learn.leetcode;

import java.util.Stack;

/**
 * 20. 有效的括号
 * {@see https://leetcode-cn.com/problems/valid-parentheses/}
 */
public class L20_IsValid {
    public static void main(String[] args) {
        System.out.println(new L20_IsValid().isValid("()[]{}"));
        System.out.println(new L20_IsValid().isValid("{[]}"));
        System.out.println(new L20_IsValid().isValid("([)]"));
    }

    public boolean isValid(String s) {
        final Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            } else {
                switch (s.charAt(i)) {
                    case ')':
                        if (!stack.isEmpty() && stack.peek() == '(') {
                            stack.pop();
                            continue;
                        }
                        return false;
                    case '}':
                        if (!stack.isEmpty() && stack.peek() == '{') {
                            stack.pop();
                            continue;
                        }
                        return false;
                    case ']':
                        if (!stack.isEmpty() && stack.peek() == '[') {
                            stack.pop();
                            continue;
                        }
                        return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
