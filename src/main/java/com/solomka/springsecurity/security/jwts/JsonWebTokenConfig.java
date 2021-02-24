package com.solomka.springsecurity.security.jwts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JsonWebTokenConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JsonWebTokenFilter jsonWebTokenFilter;

    @Autowired
    public JsonWebTokenConfig(JsonWebTokenFilter filter) {
        this.jsonWebTokenFilter = filter;
    }

    @Override
    public void configure(HttpSecurity builder) {
        builder.addFilterBefore(jsonWebTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
