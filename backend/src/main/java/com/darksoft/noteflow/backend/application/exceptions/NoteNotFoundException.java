package com.darksoft.noteflow.backend.application.exceptions;

public class NoteNotFoundException extends ApplicationException{
    public NoteNotFoundException(String message) {
        super(message);
    }
}
