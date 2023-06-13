package com.unibuc.ro.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String message) {
        super(message + " already exists in the database.");
    }
}
