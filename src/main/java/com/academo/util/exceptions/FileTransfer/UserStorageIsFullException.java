package com.academo.util.exceptions.FileTransfer;

import com.academo.model.User;

public class UserStorageIsFullException extends RuntimeException {

    public UserStorageIsFullException(String message) {
        super(message);
    }

    public UserStorageIsFullException() {
        super("Limite de armazenamento atingido. Libere espaço para novos arquivos.");
    }
}
