package com.example.authdemo.learn.lambda;

import lombok.Data;
import lombok.SneakyThrows;

import java.io.*;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * JDK中Lambda表达式的序列化与SerializedLambda的巧妙使用
 * {@see https://www.cnblogs.com/throwable/p/15611586.html}
 *
 * @author szj
 */
public class SerializedLambdaLearn {

    @SneakyThrows
    public static void main(String[] args) {
        final A a = new A();
        a.setName("Tony");
        System.out.println(method(a::getName));
        System.out.println("-------");
        System.out.println(method(() -> a.getName()));

        System.out.println("-------");

        final DemoFunction<String, String> function = String::valueOf;
        final Method method = function.getClass().getDeclaredMethod("writeReplace");
        method.setAccessible(true);
        final SerializedLambda lambda = (SerializedLambda) method.invoke(function);
        System.out.println(lambda.getImplMethodName());
        System.out.println(lambda.getImplMethodKind());
        System.out.println(lambda.getCapturedArgCount());

        // 序列化
        System.out.println("-------");
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(function);
        oos.close();

        final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        final ObjectInputStream objectInputStream = new ObjectInputStream(bais);
        final Object readObject = objectInputStream.readObject();
        System.out.println(readObject.getClass());
        final DemoFunction<String, String> readFunction = (DemoFunction<String, String>) readObject;
        System.out.println(readFunction.apply("1231"));
    }

    @SneakyThrows
    public static String method(DemoConsumer<String> consumer) {
        final Method method = consumer.getClass().getDeclaredMethod("writeReplace");
        method.setAccessible(true);
        final SerializedLambda lambda = (SerializedLambda) method.invoke(consumer);
        System.out.println("getImplMethodName=                      " + lambda.getImplMethodName());
        System.out.println("getImplClass=                           " + lambda.getImplClass());
        System.out.println("getImplMethodKind=                      " + lambda.getImplMethodKind());
        System.out.println("getImplMethodSignature=                 " + lambda.getImplMethodSignature());
        System.out.println("getFunctionalInterfaceMethodName=       " + lambda.getFunctionalInterfaceMethodName());
        System.out.println("getFunctionalInterfaceMethodSignature=  " + lambda.getFunctionalInterfaceMethodSignature());
        System.out.println("getCapturedArgCount=                    " + lambda.getCapturedArgCount());
        System.out.println("getCapturedArgCount=                    " + lambda.getCapturedArg(0));
        return consumer.accept();
    }

    @Data
    public static class A {
        private String name;
    }

    @FunctionalInterface
    public static interface DemoConsumer<T> extends Serializable {
        T accept();
    }

    @FunctionalInterface
    public interface DemoFunction<T, R> extends Serializable {
        R apply(T t);
    }
}
