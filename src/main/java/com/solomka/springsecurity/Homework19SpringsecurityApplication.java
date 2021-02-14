package com.solomka.springsecurity;

import com.solomka.springsecurity.daos.UserDao;
import com.solomka.springsecurity.models.User;
import com.solomka.springsecurity.models.UserRole;
import com.solomka.springsecurity.security.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Homework19SpringsecurityApplication {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncode;

    @Autowired
    public Homework19SpringsecurityApplication(UserDao userDao, PasswordEncoder passwordEncode) {
        this.userDao = userDao;
        this.passwordEncode = passwordEncode;

    }

    public static void main(String[] args) {
        SpringApplication.run(Homework19SpringsecurityApplication.class, args);
    }

    @PostConstruct
    public void addUsers(){
        User admin = new User();
        admin.setLogin("admin");
        admin.setPassword(passwordEncode.encode("123456"));
        admin.setRole(UserRole.LIBRARIAN);
        userDao.save(admin);

        User user = new User();
        admin.setLogin("user");
        admin.setPassword(passwordEncode.encode("123456"));
        admin.setRole(UserRole.READER);
        userDao.save(user);
    }

}
