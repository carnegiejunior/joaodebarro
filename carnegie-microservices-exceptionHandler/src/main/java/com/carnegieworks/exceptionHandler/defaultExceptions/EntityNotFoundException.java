package com.carnegieworks.exceptionHandler.defaultExceptions;

import java.io.Serial;

public class EntityNotFoundException extends BusinessException{

    @Serial
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }

}
