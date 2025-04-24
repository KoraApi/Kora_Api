package com.kora.koramonoapi.Shared.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, Integer resourceId) {
        super(String.format("%s with id %d not found.", resourceName, resourceId));
    }
    public ResourceNotFoundException(String resourceName, String message) {
        super(String.format("%s: %s", resourceName, message));
    }
}