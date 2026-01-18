package com.darksoft.noteflow.backend.application.ports;

import com.darksoft.noteflow.backend.domain.entities.Note;
import org.springframework.stereotype.Repository;

@Repository
public interface INoteRepository {
    void save (Note note);
}
