package com.example.authdemo.learn.jvm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * 一些jvm的知识
 */
public class JvmLearn {

    // jvm内存区域包括：堆，方法区，虚拟机栈，程序计数器，本地方发栈
    // 线程共享的有：堆，方法区
    // 不会内存溢出的：程序计数器


    // jvm内存溢出示例
    static class CustomObj {
    }

    @Test
    public void heapOverflow() {
        ArrayList<CustomObj> list = new ArrayList<>();
        while (true) {
            list.add(new CustomObj());
        }
    }

    // 虚拟机栈和本地方法栈溢出
    void stackLeak() {
        stackLeak();
    }

    @Test
    public void javaVMStackSOF() {
        JvmLearn jvmLearn = new JvmLearn();
        while (true) {
            jvmLearn.stackLeak();
        }
    }

}
