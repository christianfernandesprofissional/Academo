package com.academo.util.exceptions.FileTransfer;

public class FileStorageException extends RuntimeException {

    public FileStorageException(){
        super("Erro na manipulação do arquivo!");
    }

    public FileStorageException(String msg){
        super(msg);
    }
}
