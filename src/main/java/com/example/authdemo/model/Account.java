package com.example.authdemo.model;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author szj
 * @date 2022/03/04 17:11
 */
@Data
public class Account {

    private String id;

    private String password;

    private String username;

    private ZonedDateTime expTime;

    public static void main(String[] args) {
        Function<String, String> function = __ -> "a" + __;
        System.out.println(function.apply("abc"));
    }
}
