package com.academo.util.exceptions.group;

public class GroupNotFoundException extends RuntimeException {

    public GroupNotFoundException() {
        super("Grupo de matérias não encontrado!");
    }

    public GroupNotFoundException(String message) {
        super(message);
    }
}
