package com.example.authdemo.model;

import lombok.Data;

import java.time.ZonedDateTime;

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
}
