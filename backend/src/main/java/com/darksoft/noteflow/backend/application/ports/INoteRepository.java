package com.darksoft.noteflow.backend.application.ports;

import com.darksoft.noteflow.backend.domain.entities.Note;

public interface INoteRepository {
    void save (Note note);
}
