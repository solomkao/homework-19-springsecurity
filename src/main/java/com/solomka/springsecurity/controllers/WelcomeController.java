package com.solomka.springsecurity.controllers;

import com.solomka.springsecurity.daos.UserDao;
import com.solomka.springsecurity.models.User;
import com.solomka.springsecurity.models.dtos.UserDto;
import com.solomka.springsecurity.security.jwts.JsonWebTokenProvider;
import com.solomka.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WelcomeController {

    private final AuthenticationManager manager;
    private final UserService userService;
    private final JsonWebTokenProvider provider;

    @Autowired
    public WelcomeController(AuthenticationManager manager, UserService userService, JsonWebTokenProvider provider) {
        this.manager = manager;
        this.userService = userService;
        this.provider = provider;
    }

    @PostMapping(value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDto userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        User user = userService.findUserByUsername(username);
        manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String token = provider.createToken(username, user.getRole().name());
        Map<String, String> response = new HashMap<>();
        response.put("username", username);
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}