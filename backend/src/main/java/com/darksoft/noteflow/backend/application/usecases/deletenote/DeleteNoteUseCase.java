package com.darksoft.noteflow.backend.application.usecases.deletenote;

import com.darksoft.noteflow.backend.application.exceptions.NoteNotFoundException;
import com.darksoft.noteflow.backend.application.ports.INoteRepository;
import com.darksoft.noteflow.backend.domain.valueobjects.NoteId;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteNoteUseCase {

    private INoteRepository repository;

    public DeleteNoteUseCase(INoteRepository repository) {
        this.repository = repository;
    }

    public void execute(DeleteNoteCommand command){
        var noteId = new NoteId(UUID.fromString(command.noteId()));
        if(repository.findById(noteId).isEmpty()){
            throw new NoteNotFoundException("The note was not found");
        }
        this.repository.deleteById(noteId);
    }
}
