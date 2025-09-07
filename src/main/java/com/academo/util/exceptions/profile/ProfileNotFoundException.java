package com.academo.util.exceptions.profile;

public class ProfileNotFoundException extends  RuntimeException {

    public ProfileNotFoundException() {
        super("Perfil não encontrado!");
    }

    public ProfileNotFoundException(String message) {
        super(message);
    }

}
