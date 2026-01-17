package com.darksoft.noteflow.backend.cucumber.support;

public record CreateNoteRequest(String title, String content, String[] tags) {
}