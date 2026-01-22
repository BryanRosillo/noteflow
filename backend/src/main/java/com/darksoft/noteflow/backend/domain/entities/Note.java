package com.darksoft.noteflow.backend.domain.entities;

import com.darksoft.noteflow.backend.domain.exceptions.*;

import java.time.LocalDate;

public class Note {

    private String title;
    private String content;
    private Tag[] tags;
    private LocalDate creationDate = LocalDate.now();

    public Note(String title, String content, Tag[] tags){

        if(title.isBlank() || title==null){
            throw new EmptyTitleException("The title of the note cannot be empty.");
        }

        if(content.isBlank() || content==null){
            throw new EmptyContentException("The content of the note cannot be empty.");
        }

        if(content.trim().length()>500){
            throw new StringTooLongException("The content of the note cannot exceed 500 characters.");
        }

        if(title.trim().length()>100){
            throw new StringTooLongException("The title of the note cannot exceed 100 characters.");
        }

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
