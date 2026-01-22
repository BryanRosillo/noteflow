package com.darksoft.noteflow.backend.cucumber.support;

public record EditNoteRequest(String noteId, String noteTitle, String noteContent) {
}
