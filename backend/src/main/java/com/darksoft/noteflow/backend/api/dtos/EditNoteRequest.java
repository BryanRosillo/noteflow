package com.darksoft.noteflow.backend.api.dtos;

public class EditNoteRequest {

    private String noteId;
    private String newContent;
    private String newTitle;
    private String[] newTags;

    public EditNoteRequest(String noteId, String newContent, String newTitle, String[] newTags) {
        this.noteId = noteId;
        this.newContent = newContent;
        this.newTitle = newTitle;
        this.newTags = newTags;
    }

    public String getNoteId() {
        return noteId;
    }

    public String getNewContent() {
        return newContent;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public String[] getNewTags() {
        return newTags;
    }
}
