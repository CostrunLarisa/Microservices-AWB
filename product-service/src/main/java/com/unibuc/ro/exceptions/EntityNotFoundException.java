package com.unibuc.ro.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message + " does not exists in the database.");
    }

}
