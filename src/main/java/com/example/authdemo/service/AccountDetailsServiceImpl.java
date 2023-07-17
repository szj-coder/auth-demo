package com.example.authdemo.service;

import com.example.authdemo.auth.Role;
import com.example.authdemo.model.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author szj
 * @date 2022/03/04 16:58
 */
@Component
public class AccountDetailsServiceImpl implements UserDetailsPasswordService, UserDetailsService {

    private static final ConcurrentHashMap<String, AccountDetails> USER_MAP = new ConcurrentHashMap<>();
    private final PasswordEncoder passwordEncoder;

    public AccountDetailsServiceImpl(@Autowired PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        AccountDetails adminDetail = new AccountDetails();
        adminDetail.setUsername("admin");
        adminDetail.setPassword(passwordEncoder.encode("admin"));
        adminDetail.setAuthorities(Collections.singleton(new SimpleGrantedAuthority(Role.ADMIN)));
        USER_MAP.put(adminDetail.getUsername(), adminDetail);
    }

    public void save(AccountDetails details) {
        USER_MAP.put(details.getUsername(), details);
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        AccountDetails myUserDetails = USER_MAP.get(user.getUsername());
        myUserDetails.setPassword(newPassword);
        return myUserDetails;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (USER_MAP.containsKey(username)) {
            return USER_MAP.get(username);
        }
        throw new UsernameNotFoundException(String.format("用户<%s>不存在", username));
    }
}
