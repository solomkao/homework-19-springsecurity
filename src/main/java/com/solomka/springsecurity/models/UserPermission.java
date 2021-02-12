package com.solomka.springsecurity.models;

public enum UserPermission {
    READ("user:read"),
    WRITE("user:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
