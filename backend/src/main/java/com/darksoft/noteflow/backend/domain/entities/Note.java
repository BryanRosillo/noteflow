package com.darksoft.noteflow.backend.domain.entities;

import java.time.LocalDate;

public class Note {

    private String title;
    private String content;
    private Tag[] tags;
    private LocalDate creationDate = LocalDate.now();

    public Note(String title, String content, Tag[] tags) {
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Tag[] getTags() {
        return tags;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
}
