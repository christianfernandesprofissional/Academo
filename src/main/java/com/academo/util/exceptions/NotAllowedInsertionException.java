package com.academo.util.exceptions;

public class NotAllowedInsertionException extends RuntimeException {
    public NotAllowedInsertionException() {
        super("Inserção inválida!");
    }
    public NotAllowedInsertionException(String message) {
        super(message);
    }
}
