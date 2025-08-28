package com.academo.util.exceptions.activity;

public class ActivityNotFoundException extends RuntimeException {

    public ActivityNotFoundException() {
        super("Atividade não encontrada!");
    }

    public ActivityNotFoundException(String message) {
        super(message);
    }

}
