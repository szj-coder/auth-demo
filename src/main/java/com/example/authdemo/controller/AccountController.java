package com.example.authdemo.controller;

import com.example.authdemo.dao.AccountDao;
import com.example.authdemo.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    private final AccountDao accountDao;

    @GetMapping
    public Account getByUsername(@RequestParam String username) {
        final Account account = new Account();
        account.setUsername(username);
        return accountDao.findOne(Example.of(account)).orElseThrow(() ->
                new RuntimeException("username not exist."));
    }
}
