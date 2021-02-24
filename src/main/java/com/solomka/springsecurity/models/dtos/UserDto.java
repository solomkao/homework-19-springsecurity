package com.solomka.springsecurity.models.dtos;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
}
