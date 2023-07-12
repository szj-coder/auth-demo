package com.example.authdemo.config;

import com.example.authdemo.dao.AccountDao;
import com.example.authdemo.model.Account;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.ArrayList;

@Slf4j
@Component
@AllArgsConstructor
public class RunnerListener implements ApplicationListener<ApplicationStartedEvent> {

    private final AccountDao accountDao;

    @Override
    public void onApplicationEvent(@Nonnull ApplicationStartedEvent start) {
        log.info("start listener start event");
        final ArrayList<Account> accounts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final Account account = new Account();
            account.setId(String.valueOf(i));
            account.setUsername(String.valueOf('a' + i));
            if (i == 0) {
                account.setUsername("root");
            }
            account.setPassword("123456");
            accounts.add(account);
        }
        accountDao.saveAll(accounts);
    }
}
