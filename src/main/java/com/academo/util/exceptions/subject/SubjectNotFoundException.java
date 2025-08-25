package com.academo.util.exceptions.subject;

@R
public class SubjectNotFoundException extends RuntimeException {

    public SubjectNotFoundException() {
        super("Matéria não encontrada!");
    }

    public SubjectNotFoundException(String message) {
        super(message);
    }
}
