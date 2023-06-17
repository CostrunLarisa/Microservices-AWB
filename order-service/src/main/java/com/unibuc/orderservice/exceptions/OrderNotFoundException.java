package com.unibuc.orderservice.exceptions;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
