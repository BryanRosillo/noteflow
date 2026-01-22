package com.darksoft.noteflow.backend.domain.entities;

import com.darksoft.noteflow.backend.domain.exceptions.StringTooLongException;

public class Tag {
    private String tagName;

    public Tag(String tagName){
        if(tagName.trim().length()>30){
            throw new StringTooLongException("The tag length cannot exceed 30 characters.");
        }
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
