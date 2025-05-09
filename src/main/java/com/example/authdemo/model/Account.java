package com.example.authdemo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@FieldNameConstants
@Table(name = "account", uniqueConstraints = {@UniqueConstraint(columnNames = Account.Fields.username)})
public class Account {

    @Id
    private String id;

    @Column(name = "password", length = 128)
    private String password;

    @Column(name = "username", length = 32)
    private String username;

    @Column(name = "exp_time")
    private LocalDateTime expTime;

    @Column(name = "locked")
    private Boolean locked;

    @Column(name = "enabled")
    private Boolean enabled;

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
