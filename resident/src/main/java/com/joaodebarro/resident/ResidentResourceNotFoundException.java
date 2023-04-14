package com.joaodebarro.resident;

public class ResidentResourceNotFoundException extends RuntimeException {

    public ResidentResourceNotFoundException(String message) {
        super(message);
    }

    public ResidentResourceNotFoundException(Long residentResourceId) {
        super("Resident resource not found with the given ID: %s".formatted(residentResourceId));
    }

    public ResidentResourceNotFoundException() {
        super("Resident resource not found");
    }
}
