package com.example.authdemo.learn.leetcode;

import java.util.Stack;

/**
 * 224. 基本计算器
 * {@see https://leetcode-cn.com/problems/basic-calculator/}
 */
public class L224_Calculate {
    public static void main(String[] args) {
        System.out.println(new L224_Calculate().calculate("1 + 1"));
        System.out.println(new L224_Calculate().calculate(" 2-1 + 2 "));
    }

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        int ret = 0;
        int sign = 1;
        for (int i = 0; i < s.length(); ) {
            switch (s.charAt(i)) {
                case ' ':
                    i++;
                    break;
                case '+':
                    sign = stack.peek();
                    i++;
                    break;
                case '-':
                    sign = -stack.peek();
                    i++;
                    break;
                case '(':
                    stack.push(sign);
                    i++;
                    break;
                case ')':
                    sign = stack.pop();
                    i++;
                    break;
                default:
                    int num = 0;
                    while (i < s.length() && Character.isDigit(s.charAt(i))) {
                        num = num * 10 + s.charAt(i++) - '0';
                    }
                    ret += sign * num;
            }
        }
        return ret;
    }
}