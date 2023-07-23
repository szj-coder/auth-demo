package com.example.authdemo.controller;

import com.example.authdemo.config.RunnerListener;
import com.example.authdemo.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class AccountControllerTest {

    @Autowired
    private AccountController accountController;

    @Test
    public void getByUsername() {
        final Account root = accountController.getByUsername(RunnerListener.ROOT_USERNAME);
        Assert.notNull(root, "账户信息没有初始化成功");
        Assert.isTrue(RunnerListener.ROOT_USERNAME.equals(root.getId()), "root id is not 0");
    }
}
