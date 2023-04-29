package com.carnegieworks.exceptionHandler.defaultExceptions;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException() {
        super("Resource not found");
    }

    public ResourceNotFoundException(Long resourceId) {
        super("Resource not found with given ID: %s".formatted(resourceId));
    }

    public ResourceNotFoundException(String stringParameter) {
        super("Resource not found with given string parameter: %s".formatted(stringParameter));
    }

}
