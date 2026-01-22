package com.darksoft.noteflow.backend.domain.entities;

import com.darksoft.noteflow.backend.domain.exceptions.*;
import com.darksoft.noteflow.backend.domain.valueobjects.NoteId;

import java.time.LocalDate;

public class Note {
    private NoteId id;
    private String title;
    private String content;
    private Tag[] tags;
    private LocalDate creationDate = LocalDate.now();

    public Note(NoteId id, String title, String content, Tag[] tags){
        validateTitle(title);
        validateContent(content);

        this.id = id;
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    private void validateTitle(String title){
        if(title.isBlank() || title==null){
            throw new EmptyTitleException("The title of the note cannot be empty.");
        }

        if(title.trim().length()>100){
            throw new StringTooLongException("The title of the note cannot exceed 100 characters.");
        }
    }

    private void validateContent(String content){
        if(content.isBlank() || content==null){
            throw new EmptyContentException("The content of the note cannot be empty.");
        }

        if(content.trim().length()>500){
            throw new StringTooLongException("The content of the note cannot exceed 500 characters.");
        }
    }

    public void changeTitle(String title) {
        validateTitle(title);
        this.title = title;
    }

    public void changeContent(String content) {
        validateContent(content);
        this.content = content;
    }

    public void changeTags(Tag[] tags) {
        this.tags = tags;
    }

    public NoteId getId() {
        return id;
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

    public LocalDate getCreationDate() {
        return creationDate;
    }
}
