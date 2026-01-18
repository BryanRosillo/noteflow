package com.darksoft.noteflow.backend.infrastructure.persistence;

import com.darksoft.noteflow.backend.application.ports.INoteRepository;
import com.darksoft.noteflow.backend.domain.entities.Note;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public class InMemoryNoteRepository implements INoteRepository {

    private LinkedList<Note> notes = new LinkedList<>();

    @Override
    public Note save(Note note) {
        notes.add(note);
        return notes.getLast();
    }
}
