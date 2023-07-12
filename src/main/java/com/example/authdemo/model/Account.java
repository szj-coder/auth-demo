package com.example.authdemo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@FieldNameConstants
@Table(name = "account", uniqueConstraints = {@UniqueConstraint(columnNames = Account.Fields.username)})
public class Account {

    @Id
    private String id;

    @Column(name = "password", length = 20)
    private String password;

    @Column(name = "username", length = 32)
    private String username;

    @Column(name = "exp_time")
    private LocalDateTime expTime;
}
