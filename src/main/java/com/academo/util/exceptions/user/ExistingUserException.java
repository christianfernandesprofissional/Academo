package com.academo.util.exceptions.user;

public class ExistingUserException extends RuntimeException {

    public ExistingUserException() { super("Este email já está cadastrado no sistema!"); }

    public ExistingUserException(String message) { super(message); }
}
