package com.academo.util.exceptions.FileTransfer;

public class FileNotFoundException extends RuntimeException{

    public FileNotFoundException() {
        super("Arquivo não encontrado!");
    }

    public FileNotFoundException(String message) {
        super(message);
    }
}
