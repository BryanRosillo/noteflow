package com.darksoft.noteflow.backend.api.dtos;

public class DeleteNoteRequest {

    private String noteId;

    public DeleteNoteRequest(String noteId) {
        this.noteId = noteId;
    }

    public String getNoteId() {
        return noteId;
    }
}
