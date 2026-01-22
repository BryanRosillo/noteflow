package com.darksoft.noteflow.backend.application.ports;

import com.darksoft.noteflow.backend.domain.entities.Note;
import com.darksoft.noteflow.backend.domain.valueobjects.NoteId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface INoteRepository {
    Note save (Note note);
    Optional<Note> findById(NoteId id);
}
