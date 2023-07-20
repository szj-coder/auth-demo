package com.example.authdemo.config;

import com.example.authdemo.dao.AccountDao;
import com.example.authdemo.model.Account;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.ArrayList;

@Slf4j
@Component
@AllArgsConstructor
public class RunnerListener implements ApplicationListener<ApplicationStartedEvent> {

    public static final String ROOT_USERNAME = "root";
    private final static String defaultPassword = "123456";

    private final AccountDao accountDao;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void onApplicationEvent(@Nonnull ApplicationStartedEvent start) {
        log.info("start listener start event");
        final ArrayList<Account> accounts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final Account account = new Account();
            account.setUsername("a" + i);
            if (i == 0) {
                account.setUsername(ROOT_USERNAME);
            }
            account.setId(account.getUsername());
            account.setPassword(passwordEncoder.encode(defaultPassword));
            account.setLocked(false);
            account.setEnabled(true);
            accounts.add(account);
        }
        accountDao.saveAll(accounts);
    }
}
