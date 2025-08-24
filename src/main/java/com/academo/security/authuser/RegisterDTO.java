package com.academo.security.authuser;

public record RegisterDTO(String name, String password, String email, Boolean isActive) {
}
