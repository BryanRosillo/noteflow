package com.darksoft.noteflow.backend.domain.exceptions;

public class StringTooLongException extends DomainException {
    public StringTooLongException(String message) {
        super(message);
    }
}
