package com.joaodebarro.resident.exceptionHandler.defaultExceptions;

import java.io.Serial;

public class ResourceNotFoundWithIdException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundWithIdException(Long resourceId) {
        super("Resource not found with given ID: %s".formatted(resourceId));
    }

}
