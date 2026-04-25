package com.udemy.hexagonal.application.core.exceptions;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(final String id) {
        super("Object not found with id: " + id);
    }
}
