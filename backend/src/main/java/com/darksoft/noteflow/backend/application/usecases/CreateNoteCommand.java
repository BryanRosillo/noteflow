package com.darksoft.noteflow.backend.application.usecases;

import com.darksoft.noteflow.backend.domain.entities.Tag;

public class CreateNoteCommand {
    private String title;
    private String content;
    private Tag[] tags;

    public CreateNoteCommand(String title, String content, Tag[] tags) {
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

    public Tag[] getTags() {
        return tags;
    }
}
