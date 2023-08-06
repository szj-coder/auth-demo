package com.example.authdemo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@Entity
@Table(name = "persistent_logins")
public class PersistentLogins {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "username", length = 32)
    private String username;

    @Column(name = "series", length = 64)
    private String series;

    @Column(name = "token", length = 64)
    private String token;

    @Column(name = "last_used")
    private LocalDateTime lastUsed;
}
