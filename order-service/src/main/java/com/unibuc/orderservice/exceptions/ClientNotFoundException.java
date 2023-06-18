package com.unibuc.orderservice.exceptions;

public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
