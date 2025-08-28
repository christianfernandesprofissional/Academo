package com.academo.util.exceptions.type_activity;

public class TypeActivityNotFoundException extends RuntimeException {

    public TypeActivityNotFoundException() {
        super("Tipo de Atividade não encontrado!");
    }

    public TypeActivityNotFoundException(String message) {
        super(message);
    }


}
