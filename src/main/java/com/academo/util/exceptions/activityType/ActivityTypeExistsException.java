package com.academo.util.exceptions.activityType;

public class ActivityTypeExistsException extends RuntimeException {
    public ActivityTypeExistsException() {super("Este tipo de atividade já existe!");}
    public ActivityTypeExistsException(String message) {
        super(message);
    }
}
