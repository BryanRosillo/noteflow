package com.darksoft.noteflow.backend.domain.entities;

import java.time.LocalDate;

public class Note {

    private String title;
    private String content;
    private Tag tag;
    private LocalDate creationDate = LocalDate.now();

    public Note(String title, String content, Tag tag) {
        this.title = title;
        this.content = content;
        this.tag = tag;
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

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
}
