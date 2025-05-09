package com.example.authdemo.test;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Closeable;

/**
 * try with resources 关闭多个resource 语法测试
 */
public class TryWithResourcesTest {

    public static void main(String[] args) {
        final A a2 = new A("3");
        try (A a = new A("1");
             A a1 = new A("2");
             a2) {
            System.out.println(a);
            System.out.println(a1);
            System.out.println(a2);
        }
    }

    @ToString
    @AllArgsConstructor
    static class A implements Closeable {
        private String name;

        @Override
        public void close() {
            System.out.printf("name:<%s> closed!%n", name);
        }
    }
}
