package com.academo.util.exceptions.activityType;

public class ActivityTypeNotFoundException extends RuntimeException {

    public ActivityTypeNotFoundException() {
        super("Tipo de Atividade não encontrado!");
    }

    public ActivityTypeNotFoundException(String message) {
        super(message);
    }


}
