package com.academo.util.exceptions.activity;

public class ActivityExistsException extends RuntimeException{

    public ActivityExistsException(){
        super("Activity already exists");
    }

    public ActivityExistsException(String message){
        super(message);
    }
}
