package com.example.authdemo.service;

import com.example.authdemo.dao.PersistentLoginsDao;
import com.example.authdemo.model.PersistentLogins;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

@Slf4j
@Service
public class DemoRememberMeServices extends AbstractRememberMeServices {

    public static final String REMEMBER_KEY = "demo_remember_key";
    public static final int DEFAULT_SERIES_LENGTH = 16;
    public static final int DEFAULT_TOKEN_LENGTH = 16;
    private final int seriesLength = DEFAULT_SERIES_LENGTH;
    private final int tokenLength = DEFAULT_TOKEN_LENGTH;
    private final SecureRandom random;
    @Autowired
    private PersistentLoginsDao persistentLoginsDao;

    protected DemoRememberMeServices(UserDetailsService userDetailsService) {
        super(REMEMBER_KEY, userDetailsService);
        this.random = new SecureRandom();
    }

    @Override
    protected void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication successfulAuthentication) {
        final String username = successfulAuthentication.getName();
        log.info("Creating new persistent login for user {}", username);
        final PersistentLogins persistentLogins = new PersistentLogins();
        persistentLogins.setUsername(username);
        persistentLogins.setSeries(generateSeriesData());
        persistentLogins.setToken(generateTokenData());
        persistentLogins.setLastUsed(LocalDateTime.now());
        try {
            persistentLoginsDao.save(persistentLogins);
            addCookie(persistentLogins, request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onLoginFail(HttpServletRequest request, HttpServletResponse response) {
        log.info("login fail");
        super.onLoginFail(request, response);
    }

    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request, HttpServletResponse response) throws RememberMeAuthenticationException, UsernameNotFoundException {
        return null;
    }

    private void addCookie(PersistentLogins token, HttpServletRequest request, HttpServletResponse response) {
        setCookie(new String[] { token.getSeries(), token.getToken() }, getTokenValiditySeconds(), request,
                response);
    }

    protected String generateSeriesData() {
        byte[] newSeries = new byte[this.seriesLength];
        this.random.nextBytes(newSeries);
        return new String(Base64.getEncoder().encode(newSeries));
    }

    protected String generateTokenData() {
        byte[] newToken = new byte[this.tokenLength];
        this.random.nextBytes(newToken);
        return new String(Base64.getEncoder().encode(newToken));
    }


}
