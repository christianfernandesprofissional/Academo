package com.academo.util.exceptions.activityType;

public class ActivityTypeUserIdChangedException extends RuntimeException{

    public ActivityTypeUserIdChangedException() {
        super("The ActivityTypeUserId was changed");
    }

    public ActivityTypeUserIdChangedException(String message) {
        super(message);
    }
}
