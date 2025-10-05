package com.academo.util.exceptions.user;

import com.academo.model.User;

public class UserIsNotActiveException extends RuntimeException {

    private User user;

    public UserIsNotActiveException() {
        super("Usuário não está ativo");

    }

    public UserIsNotActiveException(String message, User user) {
        super(message);

    }

    public User getUser() {
        return user;
    }
}
