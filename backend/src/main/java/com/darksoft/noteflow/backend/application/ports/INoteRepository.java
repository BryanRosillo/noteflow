package com.darksoft.noteflow.backend.application.ports;

import com.darksoft.noteflow.backend.domain.entities.Note;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface INoteRepository {
    Note save (Note note);
    Optional<Note> findById(int id);
}
