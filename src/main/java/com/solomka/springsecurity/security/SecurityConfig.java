package com.solomka.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.solomka.springsecurity.security.UserRole.LIBRARIAN;
import static com.solomka.springsecurity.security.UserRole.READER;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
//                .antMatchers(HttpMethod.GET, "/books/**").hasAuthority(UserPermission.READ.getPermission())
//                .antMatchers(HttpMethod.POST, "/books/**").hasRole(UserPermission.WRITE.getPermission())
//                .antMatchers(HttpMethod.DELETE, "/books/**").hasRole(UserPermission.WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails oksana = User.builder()
                .username("oksana")
                .password(passwordEncoder.encode("123456"))
                .authorities(LIBRARIAN.getAuthorities())
                .build();

        UserDetails olena = User.builder()
                .username("olena")
                .password(passwordEncoder.encode("123456"))
                .authorities(READER.getAuthorities())
                .build();

        return new InMemoryUserDetailsManager(oksana, olena);
    }
}
