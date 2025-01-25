package com.combatsasality.crm.persistence.exceptions;

public class BadValidationException extends Exception{
    public BadValidationException(String errorMessage) {
        super(errorMessage);
    }
}
