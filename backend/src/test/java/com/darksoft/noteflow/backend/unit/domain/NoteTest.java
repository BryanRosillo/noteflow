package com.darksoft.noteflow.backend.unit.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.darksoft.noteflow.backend.domain.entities.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NoteTest {

    @Test
    @DisplayName("Create a valid note with tags")
    public void create_a_valid_note_with_tags(){
        String title = "Do my Homework";
        String content = "I must end my math homework on typical operations.";
        Tag[] tags = new Tag[]{new Tag("Homework")};

        Note note = new Note(title, content, tags);

        assertEquals(title, note.getTitle());
        assertEquals(content, note.getContent());
        assertEquals(tags[0].getTagName(), note.getTags()[0].getTagName());
    }

    @Test
    @DisplayName("Create a note without title")
    public void create_a_note_without_title(){
        String title = "";
        String content = "It's a note";
        Tag[] tags = new Tag[]{new Tag("Tag01")};

        var exception = assertThrows(EmptyClassException.class, () -> new Note(title, content, tags));

        assertEquals("The title of the note cannot be empty.", exception.getMessage());
    }
}
