package com.example.authdemo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Java 泛型擦除测试 {@see https://www.cnblogs.com/wuqinglong/p/9456193.html}<br/>
 * Java 动态绑定和静态绑定 {@see https://www.jianshu.com/p/4277bbcab647}
 */
public class GenericsTest {

    public static class A {
        public void print(C c) {
            c.print(this);
        }
    }

    public static class B extends A {
        public void print(C c) {
            c.print(this);
        }
    }

    public static class C {
        public void print(A a) {
            System.out.println("is A");
        }

        public void print(B b) {
            System.out.println("is B");
        }
    }

    /**
     * 控制台输出内容
     * <p>
     * is A<br/>
     * is A<br/>
     * <br/>
     * is B<br/>
     * is A<br/>
     * </p>
     */
    public static void main(String[] args) {
        final List<A> as = new ArrayList<>();
        as.add(new B());
        as.add(new A());
        final C c = new C();
        for (A a : as) {
            c.print(a);
        }
        System.out.println();
        for (A a : as) {
            a.print(c);
        }
    }
}
