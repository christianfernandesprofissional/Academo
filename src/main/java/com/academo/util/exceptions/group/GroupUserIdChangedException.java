package com.academo.util.exceptions.group;

public class GroupUserIdChangedException extends RuntimeException{
    public GroupUserIdChangedException() {
        super("The GroupUserId was changed");
    }

    public GroupUserIdChangedException(String message) {
        super(message);
    }
}
