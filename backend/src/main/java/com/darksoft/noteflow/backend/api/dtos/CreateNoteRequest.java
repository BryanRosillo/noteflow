package com.darksoft.noteflow.backend.api.dtos;

public class CreateNoteRequest {
    private String title;
    private String content;
    private String[] tags;

    public CreateNoteRequest(String title, String content, String[] tags) {
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String[] getTags() {
        return tags;
    }
}
