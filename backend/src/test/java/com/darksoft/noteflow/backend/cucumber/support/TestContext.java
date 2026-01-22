package com.darksoft.noteflow.backend.cucumber.support;

import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class TestContext {

    private String noteId;
    private String noteTitle;
    private String noteContent;
    private String[] noteTags;
    private ResponseEntity<String> response;

    public String getNoteId() {return noteId;}

    public void setNoteId(String noteId) {this.noteId = noteId;}

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String[] getNoteTags() {
        return noteTags;
    }

    public void setNoteTags(String[] noteTags) {
        this.noteTags = noteTags;
    }

    public ResponseEntity<String> getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity<String> response) {
        this.response = response;
    }

}
