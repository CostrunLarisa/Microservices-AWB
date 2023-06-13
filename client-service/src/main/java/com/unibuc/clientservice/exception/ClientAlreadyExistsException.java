package com.unibuc.clientservice.exception;

public class ClientAlreadyExistsException extends RuntimeException {
    public ClientAlreadyExistsException(String message) {
        super(message);
    }

    public ClientAlreadyExistsException(String message, Throwable throwable) {

        super(message, throwable);
    }
}
