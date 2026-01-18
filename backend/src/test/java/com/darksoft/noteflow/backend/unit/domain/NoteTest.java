package com.darksoft.noteflow.backend.unit.domain;


import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoteTest {

    @DisplayName("Create a valid note with tags")
    public void create_a_valid_note_with_tags(){
        String title = "Do my Homework";
        String content = "I must end my math homework on typical operations.";
        Tag tag = new Tag("Homework");

        Note note = new Note(title, content, tag);

        assertEquals(title, note.getContent());
        assertEquals(content, note.getContet());
        assertEquals(tag.getTagName(), note.getTag().getTagName());
    }
}
