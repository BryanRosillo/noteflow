package com.darksoft.noteflow.backend.infrastructure.persistence;

import com.darksoft.noteflow.backend.application.ports.INoteRepository;
import com.darksoft.noteflow.backend.domain.entities.Note;
import com.darksoft.noteflow.backend.domain.valueobjects.NoteId;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryNoteRepository implements INoteRepository {

    private LinkedList<Note> notes = new LinkedList<>();

    @Override
    public Note save(Note note) {
        notes.add(note);
        return notes.getLast();
    }

    @Override
    public Optional<Note> findById(NoteId id) {
        return notes.stream()
                .filter(note -> note.getId().equals(id))
                .findFirst();
    }

    @Override
    public void deleteById(NoteId id) {
        notes.removeIf(note -> note.getId().equals(id));
    }

}
