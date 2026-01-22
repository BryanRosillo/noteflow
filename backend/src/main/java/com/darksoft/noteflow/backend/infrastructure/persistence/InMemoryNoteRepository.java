package com.darksoft.noteflow.backend.infrastructure.persistence;

import com.darksoft.noteflow.backend.application.ports.INoteRepository;
import com.darksoft.noteflow.backend.domain.entities.Note;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.Optional;

@Repository
public class InMemoryNoteRepository implements INoteRepository {

    private LinkedList<Note> notes = new LinkedList<>();

    @Override
    public Note save(Note note) {
        notes.add(note);
        return notes.getLast();
    }

    @Override
    public Optional<Note> findById(int id) {
        return Optional.empty();
    }


}
