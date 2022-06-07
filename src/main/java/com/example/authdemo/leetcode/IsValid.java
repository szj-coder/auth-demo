package com.example.authdemo.leetcode;

import java.util.Stack;

/**
 * 20. 有效的括号
 * {@see https://leetcode-cn.com/problems/valid-parentheses/}
 */
public class IsValid {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
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

    public static void main(String[] args) {
        System.out.println(new IsValid().isValid("()[]{}"));
        System.out.println(new IsValid().isValid("{[]}"));
        System.out.println(new IsValid().isValid("([)]"));
    }
}
