package com.darksoft.noteflow.backend.unit.domain;

import com.darksoft.noteflow.backend.domain.exceptions.DomainException;
import com.darksoft.noteflow.backend.domain.exceptions.EmptyContentException;
import com.darksoft.noteflow.backend.domain.exceptions.EmptyTitleException;
import com.darksoft.noteflow.backend.domain.exceptions.StringTooLongException;
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
    @DisplayName("Create a valid note without tags")
    public void create_a_valid_note_without_tags(){
        String title = "Do my Homework";
        String content = "I must end my math homework on typical operations.";

        Note note = new Note(title, content, null);

        assertEquals(title, note.getTitle());
        assertEquals(content, note.getContent());
    }

    @Test
    @DisplayName("Create a note without title")
    public void create_a_note_without_title(){
        String title = "";
        String content = "It's a note";
        Tag[] tags = new Tag[]{new Tag("Tag01")};

        var exception = assertThrows(EmptyTitleException.class, () -> new Note(title, content, tags));

        assertEquals("The title of the note cannot be empty.", exception.getMessage());
    }

    @Test
    @DisplayName("Create a note without content")
    public void create_a_note_without_content(){
        String title = "Title01";
        String content = " ";
        Tag[] tags = new Tag[]{new Tag("Tag01")};

        var exception = assertThrows(EmptyContentException.class, () -> new Note(title, content, tags));

        assertEquals("The content of the note cannot be empty.", exception.getMessage());
    }

    @Test
    @DisplayName("Create a note with content longer than 500 characters")
    public void create_a_note_with_content_longer_than_500_characters(){
        String title = "Title01";
        String content = "a".repeat(500+1);

        Tag[] tags = new Tag[]{new Tag("Tag01")};

        var exception = assertThrows(StringTooLongException.class, () -> new Note(title, content, tags));

        assertEquals("The content of the note cannot exceed 500 characters.", exception.getMessage());
    }

    @Test
    @DisplayName("Create a note with title longer than 100 characters")
    public void create_a_note_with_title_longer_than_100_characters(){
        String title = "a".repeat(100+1);
        String content = "Wow, the title is too long.";

        Tag[] tags = new Tag[]{new Tag("Tag01")};

        var exception = assertThrows(StringTooLongException.class, () -> new Note(title, content, tags));

        assertEquals("The title of the note cannot exceed 100 characters.", exception.getMessage());
    }
}
