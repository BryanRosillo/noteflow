package com.darksoft.noteflow.backend.cucumber.support;

public record EditNoteRequest(int noteId, String noteTitle, String noteContent) {
}
