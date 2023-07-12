package com.example.authdemo.learn.leetcode;

import java.util.Stack;

public class L09CQueue {

    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        cQueue.appendTail(3);
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
    }

    static class CQueue {

        private Stack<Integer> mStack = new Stack<>();
        private Stack<Integer> cStack = new Stack<>();

        public CQueue() {
        }

        public void appendTail(int value) {
            mStack.add(value);
        }

        public int deleteHead() {
            Stack<Integer> stack = mStack;
            if (stack.isEmpty() && cStack.isEmpty()) {
                return -1;
            }
            if (cStack.isEmpty()) {
                while (!stack.isEmpty()) {
                    cStack.add(stack.pop());
                }
            }
            return cStack.pop();
        }
    }
}
