package com.unibuc.clientserver.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ClientAlreadyExistsException.class,
            ClientNotFoundException.class})
    public ResponseEntity<?> handleCustomInternalException(RuntimeException exception) {
        Map<String, Object> responseParameters = new HashMap<>();
        responseParameters.put("Reason", exception.getMessage());
        responseParameters.put("DateTime", LocalDateTime.now().toString());

        return ResponseEntity.badRequest().body(responseParameters);
    }
}
