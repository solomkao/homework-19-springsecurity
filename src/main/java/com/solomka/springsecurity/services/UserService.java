package com.solomka.springsecurity.services;

import com.solomka.springsecurity.daos.UserDao;
import com.solomka.springsecurity.models.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findUserByUsername(String username) {
        return userDao.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("There's no such user " + username));
    }
}
