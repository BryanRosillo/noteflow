package com.darksoft.noteflow.backend.unit.domain;

import com.darksoft.noteflow.backend.domain.entities.Tag;
import com.darksoft.noteflow.backend.domain.exceptions.StringTooLongException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TagTest {

    @Test
    @DisplayName("Create a note with a tag longer than 30 characters")
    public void create_a_note_with_a_tag_longer_than_30_characters(){
        String tagName = "a".repeat(30+1);
        var exception = assertThrows(StringTooLongException.class, ()->new Tag(tagName));

        assertEquals("The tag length cannot exceed 30 characters.", exception.getMessage());
    }
}
