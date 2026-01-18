package com.darksoft.noteflow.backend.infrastructure.persistence;

import com.darksoft.noteflow.backend.application.ports.INoteRepository;
import com.darksoft.noteflow.backend.domain.entities.Note;

import java.util.LinkedList;

public class InMemoryNoteRepository implements INoteRepository {

    private LinkedList<Note> notes = new LinkedList<>();

    @Override
    public void save(Note note) {
        notes.add(note);
    }
}
