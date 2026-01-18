package com.darksoft.noteflow.backend.application.usecases;

import com.darksoft.noteflow.backend.application.ports.INoteRepository;
import com.darksoft.noteflow.backend.domain.entities.Note;

public class CreateNoteUseCase {

    private INoteRepository repository;

    public CreateNoteUseCase(INoteRepository repository) {
        this.repository = repository;
    }

    public void createNote(CreateNoteCommand command){

        Note note = new Note(
                command.getTitle(),
                command.getContent(),
                command.getTags()
        );

        this.repository.save(note);
    }
}
