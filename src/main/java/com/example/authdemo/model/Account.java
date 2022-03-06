package com.example.authdemo.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.time.ZonedDateTime;
import java.util.List;

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
