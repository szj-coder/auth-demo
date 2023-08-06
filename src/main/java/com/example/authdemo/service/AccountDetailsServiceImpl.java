package com.example.authdemo.service;

import com.example.authdemo.dao.AccountDao;
import com.example.authdemo.model.Account;
import com.example.authdemo.model.AccountDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author szj
 * @date 2022/03/04 16:58
 */
@Component
@AllArgsConstructor
public class AccountDetailsServiceImpl implements UserDetailsPasswordService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final AccountDao accountDao;

    public void save(AccountDetails details) {
        if (StringUtils.hasText(details.getPassword())) {
            details.getAccount().setPassword(passwordEncoder.encode(details.getPassword()));
        }
        accountDao.save(details.getAccount());
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        final AccountDetails accountDetails = (AccountDetails) user;
        final Account saveAccount = accountDao.save(accountDetails.getAccount());

        final AccountDetails resultDetails = new AccountDetails(saveAccount);
        resultDetails.setAuthorities(accountDetails.getAuthorities());
        return resultDetails;
    }

    /**
     * {@link UsernamePasswordAuthenticationFilter} 匹配到登录地址，路由到这里
     *
     * @param username the username identifying the user whose data is required.
     * @return 用户明细
     * @throws UsernameNotFoundException 用户不存在
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Account account = accountDao.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("用户<%s>不存在", username)));
        // todo
//        userDetails.setAuthorities();
        return new AccountDetails(account);
    }
}
